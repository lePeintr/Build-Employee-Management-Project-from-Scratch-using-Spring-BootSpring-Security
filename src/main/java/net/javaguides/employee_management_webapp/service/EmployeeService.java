package net.javaguides.employee_management_webapp.service;

import net.javaguides.employee_management_webapp.model.Employee;
import org.springframework.data.domain.Page;


import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void saveEmployee(Employee employee);
    Employee getEmployeeById(Long id);
    void deleteEmployeeById(Long id);
    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
