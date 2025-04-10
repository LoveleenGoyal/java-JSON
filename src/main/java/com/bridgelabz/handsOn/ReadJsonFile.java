package com.bridgelabz.handsOn;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

class User {
    private String name;
    private int age;
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
public class ReadJsonFile {
    public static void main(String[] args) {
        String file = "files/user.json";

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<User> users = objectMapper.readValue(new File(file), new TypeReference<List<User>>() {});

            for (User user : users) {
                Map<String, Object> map = objectMapper.convertValue(user, new TypeReference<Map<String, Object>>() {});
                for (String key : map.keySet()) {
                    System.out.println(key + ": " + map.get(key));
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
