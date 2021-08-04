package com.cleviro.ErpManagerApp.repository.people;

import com.cleviro.ErpManagerApp.model.people.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
