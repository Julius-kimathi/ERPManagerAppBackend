package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;

import java.util.Collection;

public interface DepartmentService {
    /**
     * Get a department by id
     * @param id
     */
    DepartmentDto findDepartmentById(Short id);

    /**
     * Find all the departments
     * @param
     */
    Collection<DepartmentDto> findAllDepartments();

    /**
     *
     * Create a new department
     * @param departmentDto
     */
    DepartmentDto addDepartment(DepartmentDto departmentDto);

    /**
     * update department details
     * @param departmentDto
     */
    DepartmentDto updateDepartment(DepartmentDto departmentDto);

    /**
     * delete a department from db
     * @param id
     */
    void removeDepartment(Short id);
}
