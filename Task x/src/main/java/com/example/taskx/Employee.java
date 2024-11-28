package com.example.taskx;

public abstract class Employee {
    private String name;
    private String type;

    public Employee(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public abstract double calculateSalary();
}

