package repository;

import model.Employee;
import java.util.List;

public interface EmployeeRepository {
    void save(Employee employee);
    List<Employee> findAll();
    void update(Employee employee);
    void delete(int employeeId);

}
