package com.bridgelabz.practice;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

class Person {
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
public class ListToJSONArray {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Loveleen", 21),
                new Person("Yagyata", 22)
        );


        JSONArray jsonArray = new JSONArray();
        for (Person p : people) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("Name", p.name);
            jsonObject.put("Age", p.age);
            jsonArray.put(jsonObject);
        }

        System.out.println(jsonArray.toString(2));
    }
}
