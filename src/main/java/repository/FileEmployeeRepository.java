package repository;

import model.*;
import java.io.*;
import java.util.*;

public class FileEmployeeRepository implements EmployeeRepository {

    private static final String FILE_PATH = "data/employees.txt";

    public FileEmployeeRepository() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("Type,ID,Name,Value1,Value2\n");
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    @Override
    public void save(Employee employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(employee.toFileString() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving employee: " + e.getMessage());
        }
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) { // skip header
                    firstLine = false;
                    continue;
                }
                String[] data = line.split(",");
                if (data.length < 5) continue;
                String type = data[0].trim();
                int id = Integer.parseInt(data[1].trim());
                String name = data[2].trim();
                if (type.equals("FULL")) {
                    double salary = Double.parseDouble(data[3].trim());
                    double benefits = Double.parseDouble(data[4].trim());
                    employees.add(new FullTimeEmployee(id, name, salary, benefits));
                } else if (type.equals("PART")) {
                    int hours = Integer.parseInt(data[3].trim());
                    double rate = Double.parseDouble(data[4].trim());
                    employees.add(new PartTimeEmployee(id, name, hours, rate));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading employee file: " + e.getMessage());
        }
        return employees;
    }

    @Override
    public void update(Employee employee) {
        List<Employee> all = findAll();
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            fw.write("Type,ID,Name,Value1,Value2\n"); // header
            for (Employee e : all) {
                if (e.getId() == employee.getId()) {
                    fw.write(employee.toFileString() + "\n");
                } else {
                    fw.write(e.toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error updating employee: " + e.getMessage());
        }
    }

    @Override
    public void delete(int employeeId) {
        List<Employee> all = findAll();
        try (BufferedWriter fw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            fw.write("Type,ID,Name,Value1,Value2\n"); // header
            for (Employee e : all) {
                if (e.getId() != employeeId) {
                    fw.write(e.toFileString() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }
}
