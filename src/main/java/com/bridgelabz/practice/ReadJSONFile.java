package com.bridgelabz.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
public class ReadJSONFile {
    public static void main(String[] args) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            List<User> users = objectMapper.readValue(new File("files/user.json"), new TypeReference<List<User>>() {});
            for (User user : users) {
                System.out.println("Name: " + user.getName());
                System.out.println("Age: " + user.getAge());
                System.out.println("Email: " + user.getEmail());
                System.out.println("-----");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
