package model;

import exception.InvalidSalaryException;

public abstract class Employee {

    private int id;
    private String name;

    protected Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    // Polymorphism
    public abstract double calculateSalary() throws InvalidSalaryException;


    // Used for file saving
    public abstract String toFileString();
}
