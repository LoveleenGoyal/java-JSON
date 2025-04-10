package com.bridgelabz.handsOn;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Student {
    public int id;
    public String name;
    public int age;
    public int marks;

    public Student() {}

    public Student(int id, String name, int age, int marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }
}

public class CSVToJSON {
    public static void main(String[] args) throws IOException {
        String csvFile = "files/students.csv";
        String csvToJsonFile = "files/jsonStudents.json";

        List<Student> studentList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            reader.readLine();

            String line;
            while((line = reader.readLine()) != null) {
                String[] column = line.split(",");
                int id = Integer.parseInt(column[0]);
                String name = column[1];
                int age = Integer.parseInt(column[2]);
                int marks = Integer.parseInt(column[3]);

                Student student = new Student(id,name,age,marks);
                studentList.add(student);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(csvToJsonFile), studentList);
        System.out.println("Converted to JSON");

    }
}
