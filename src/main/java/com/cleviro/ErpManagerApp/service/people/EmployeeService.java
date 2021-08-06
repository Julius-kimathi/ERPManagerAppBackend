package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;

import java.util.Collection;

public interface EmployeeService {
    /**
     * Get a employee by id
     * @param id
     */
    EmployeeDto findEmployeeById(Long id);

    /**
     * Get a employee by id
     * @param idNo
     */
    EmployeeDto findEmployeeByIdNo(String idNo);

    /**
     * Get a employee by id
     * @param email
     */
    EmployeeDto findEmployeeByEmail(String email);

    /**
     * Find all the employees
     * @param
     */
    Collection<EmployeeDto> findAllEmployees();

    /**
     *
     * Create a new employee
     * @param employeeDto
     */
    EmployeeDto addEmployee(EmployeeDto employeeDto);

    /**
     * update employee details
     * @param employeeDto
     */
    EmployeeDto updateEmployee(EmployeeDto employeeDto);

    /**
     * delete a employee from db
     * @param id
     */
    void removeEmployee(Long id);

}
