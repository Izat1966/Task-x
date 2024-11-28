package com.example.taskx;

public class Contractor extends Employee {
    private double hourlyRate;
    private double maxHours;

    public Contractor(String name, double hourlyRate, double maxHours) {
        super(name, "Contractor");
        this.hourlyRate = hourlyRate;
        this.maxHours = maxHours;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * maxHours;
    }
}

