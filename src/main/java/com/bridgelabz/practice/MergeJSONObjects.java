package com.bridgelabz.practice;

import org.json.JSONObject;

public class MergeJSONObjects {
    public static void main(String[] args) {
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("Name1", "Loveleen");
        jsonObject1.put("Age1", 21);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("Name2", "Yagyata");
        jsonObject2.put("Age2", 22);

        JSONObject merged = new JSONObject();
        merged.put("Student 1", jsonObject1);
        merged.put("Student 2", jsonObject2);

        System.out.println(merged.toString(2));
    }
}
