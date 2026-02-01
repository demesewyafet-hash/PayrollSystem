package model;

import exception.InvalidSalaryException;

public class FullTimeEmployee extends Employee {

    private double baseSalary;
    private double benefits;
    private double taxRate = 0.10;

    public FullTimeEmployee(int id, String name, double baseSalary, double benefits) {
        super(id, name);
        this.baseSalary = baseSalary;
        this.benefits = benefits;
    }

    @Override
    public double calculateSalary() throws InvalidSalaryException {
        if (baseSalary < 0)
            throw new InvalidSalaryException("Base salary cannot be negative");
        double gross = baseSalary + benefits;
        double tax = gross * taxRate;
        return gross - tax;

    }

    @Override
    public String toFileString() {
        try {
            return getName() + " (ID: " + getId() + ") - Salary: $" + calculateSalary();
        } catch (InvalidSalaryException e) {
            return getName() + " (ID: " + getId() + ") - Salary: ERROR";
        }
    }
}