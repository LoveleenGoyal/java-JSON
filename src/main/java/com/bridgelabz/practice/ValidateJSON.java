package com.bridgelabz.practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ValidateJSON {
    public static void main(String[] args) {
        String validJson = "{\"name\":\"Loveleen\", \"age\":24, \"email\":\"loveleen@example.com\"}";
        String invalidJson = "{\"name\",\"Love\", \"email\":\"love@example.com\"}";

        validateJsonStructure(validJson);
        validateJsonStructure(invalidJson);
    }

    public static void validateJsonStructure(String json) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            JsonNode jsonNode = objectMapper.readTree(json);
            System.out.println("Valid Json: " + jsonNode.toString());
        } catch (JsonProcessingException e) {
            System.out.println("Invalid Json: " + e.getMessage());
        }
    }
}
