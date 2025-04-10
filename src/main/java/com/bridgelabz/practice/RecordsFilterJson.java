package com.bridgelabz.practice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

class Users {
    public String name;
    public int age;
    public String email;
    public Users() {
    }

    public Users(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
}
public class RecordsFilterJson {
    public static void main(String[] args) {
        String jsonFile = "files/user.json";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<Users> users = objectMapper.readValue(new File(jsonFile), new TypeReference<List<Users>>() {});

            for (Users u : users) {
                if (u.age > 25) {
                    System.out.println("Name: " + u.name);
                    System.out.println("Age:" + u.age);
                    System.out.println("Email: " + u.email);
                    System.out.println("-----------------------");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
