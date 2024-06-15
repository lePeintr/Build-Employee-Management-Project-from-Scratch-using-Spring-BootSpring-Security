package net.javaguides.employee_management_webapp.repository;

import net.javaguides.employee_management_webapp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
