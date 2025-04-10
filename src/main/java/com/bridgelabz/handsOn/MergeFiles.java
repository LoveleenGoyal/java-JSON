package com.bridgelabz.handsOn;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.*;
import java.util.*;

public class MergeFiles {
    public static void main(String[] args) {
        String file1 = "files/person.json";
        String file2 = "files/personAddress.json";

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode json1 = mapper.readTree(new File(file1));
            JsonNode json2 = mapper.readTree(new File(file2));

            ObjectNode mergedJson = (ObjectNode) json1;

            Iterator<Map.Entry<String, JsonNode>> fields = json2.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                mergedJson.set(entry.getKey(), entry.getValue());
            }

            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mergedJson));

        } catch (IOException e) {
            System.out.println("Failed to merge " + e.getMessage());
        }
    }
}
