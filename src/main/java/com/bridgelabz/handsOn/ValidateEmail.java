package com.bridgelabz.handsOn;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.*;

import java.io.*;
import java.util.Set;

public class ValidateEmail {
    public static void main(String[] args) throws IOException {
        String file = "files/users.json";
        String schemaFile = "files/userSchema.json";

        ObjectMapper mapper = new ObjectMapper();

        JsonNode schemaNode = mapper.readTree(new File(schemaFile));
        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema jsonSchema = factory.getSchema(schemaNode);

        JsonNode jsonNode = mapper.readTree(new File(file));


        Set<ValidationMessage> errors = jsonSchema.validate(jsonNode);

        if (errors.isEmpty()) {
            System.out.println("All emails are valid.");
        } else {
            System.out.println("Invalid email or JSON structure:");
            for (ValidationMessage error : errors) {
                System.out.println(" - " + error.getMessage());
            }
        }
    }
}
