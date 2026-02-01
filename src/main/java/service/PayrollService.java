package service;

import model.Employee;
import repository.EmployeeRepository;

import java.util.List;

public class PayrollService {

    private final EmployeeRepository repository;

    public PayrollService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public void addEmployee(Employee employee) {
        repository.save(employee);
    }

    public void update(Employee e) {
        repository.update(e);
    }

    public void delete(int id) {
        repository.delete(id);
    }
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
}
