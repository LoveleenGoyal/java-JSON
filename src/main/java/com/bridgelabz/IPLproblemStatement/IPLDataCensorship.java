package com.bridgelabz.IPLproblemStatement;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.opencsv.*;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.util.*;

public class IPLDataCensorship {
    public static void main(String[] args) {
        String jsonInput = "files/ipl_matches.json";
        String csvInput = "files/ipl_matches.csv";
        String jsonOutput = "files/censored_matches.json";
        String csvOutput = "files/censored_matches.csv";

        try {
            censorJson(jsonInput, jsonOutput);
            censorCsv(csvInput, csvOutput);
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Censorship completed.");
    }

    private static void censorJson(String jsonInput, String jsonOutput) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new File(jsonInput));

        for (JsonNode match : root) {
            // Mask team names
            String team1 = match.get("team1").asText();
            String team2 = match.get("team2").asText();
            ((ObjectNode) match).put("team1", maskTeamName(team1));
            ((ObjectNode) match).put("team2", maskTeamName(team2));

            // Redact player of the match
            ((ObjectNode) match).put("player_of_match", "REDACTED");

            // Mask score keys
            JsonNode score = match.get("score");
            Map<String, Integer> newScore = new LinkedHashMap<>();

            Iterator<Map.Entry<String, JsonNode>> fields = score.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                newScore.put(maskTeamName(entry.getKey()), entry.getValue().asInt());
            }
            ((ObjectNode) match).putPOJO("score", newScore);

            // Mask winner
            ((ObjectNode) match).put("winner", maskTeamName(match.get("winner").asText()));
        }

        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonOutput), root);
    }

    private static void censorCsv(String csvInput, String csvOutput) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new FileReader(csvInput));
             CSVWriter writer = new CSVWriter(new FileWriter(csvOutput))) {
            List<String[]> allRows = reader.readAll();
            List<String[]> modifiedRows = new ArrayList<>();

            // First add header row
            modifiedRows.add(allRows.get(0));

            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                if (row.length < 7) continue; // Skip malformed rows
                row[1] = maskTeamName(row[1]); // team1
                row[2] = maskTeamName(row[2]); // team2
                row[5] = maskTeamName(row[5]); // winner
                row[6] = "REDACTED"; // player_of_match
                modifiedRows.add(row);
            }

            writer.writeAll(modifiedRows);
        }
    }
    private static String maskTeamName(String teamName) {
        int spaceIndex = teamName.indexOf(' ');
        if (spaceIndex == -1) return "***";
        return teamName.substring(0, spaceIndex) + " ***";
    }
}
