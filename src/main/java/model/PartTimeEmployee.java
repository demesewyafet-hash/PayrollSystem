package model;

import exception.InvalidSalaryException;

public class PartTimeEmployee extends Employee {

    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(int id, String name, int hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() throws InvalidSalaryException {
        if (hoursWorked < 0 || hourlyRate < 0)
            throw new InvalidSalaryException("Invalid hours or rate");

        double salary = hoursWorked * hourlyRate;
        return salary - (salary * 0.05);
    }

    @Override
    public String toFileString() {
        return "PART," + getId() + "," + getName() + "," + hoursWorked + "," + hourlyRate;
    }
}
