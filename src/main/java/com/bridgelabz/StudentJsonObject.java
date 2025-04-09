package com.bridgelabz;

import org.json.JSONArray;
import org.json.JSONObject;

class Student {
    String name;
    int age;
    String[] subjects;
    Student(String name, int age, String[] subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("age", age);
        json.put("subjects", new JSONArray(subjects));
        return json;
    }
}
public class StudentJsonObject {
    public static void main(String[] args) {
        Student s1 = new Student("Loveleen", 21, new String[]{"Java", "Spring"});
        Student s2 = new Student("Yagyata", 22, new String[]{"Spring"});
        JSONObject j1 = s1.toJson();
        JSONObject j2 = s2.toJson();
        System.out.println(j1.toString(2));
        System.out.println(j2.toString(2));
    }
}
