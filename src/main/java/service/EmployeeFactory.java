package service;

import model.*;

public class EmployeeFactory {

    public static Employee createEmployee(
            String type,
            int id,
            String name,
            double salary,
            double benefits,
            int hours,
            double rate
    ) {
        return switch (type) {
            case "FULL" -> new FullTimeEmployee(id, name, salary, benefits);
            case "PART" -> new PartTimeEmployee(id, name, hours, rate);
            default -> throw new IllegalArgumentException("Invalid employee type");
        };
    }
}
