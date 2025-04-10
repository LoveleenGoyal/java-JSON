package com.bridgelabz.practice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

class Car {
    public String name;
    public String model;
    public int year;

    public Car(String name, String model, int year) {
        this.name = name;
        this.model = model;
        this.year = year;
    }
}
public class ConvertObjectToJson {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        Car car1 = new Car("Tesla", "Model S", 2022);
        Car car2 = new Car("Ford", "Mustang", 2020);
        Car car3 = new Car("Toyota", "Camry", 2023);

        List<Car> carList = Arrays.asList(car1, car2, car3);

        try {
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(carList);
            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
