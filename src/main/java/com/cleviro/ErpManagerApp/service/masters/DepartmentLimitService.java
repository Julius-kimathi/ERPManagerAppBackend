package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentLimitDto;

import java.util.Collection;

public interface DepartmentLimitService {
    /**
     * Get a departmentLimit by id
     * @param id
     */
    DepartmentLimitDto findDepartmentLimitById(Short id);

    /**
     * Find all the departmentLimits
     * @param
     */
    Collection<DepartmentLimitDto> findAllDepartmentLimits();

    /**
     *
     * Create a new departmentLimit
     * @param departmentLimitDto
     */
    DepartmentLimitDto addDepartmentLimit(DepartmentLimitDto departmentLimitDto);

    /**
     * update departmentLimit details
     * @param departmentLimitDto
     */
    DepartmentLimitDto updateDepartmentLimit(DepartmentLimitDto departmentLimitDto);

    /**
     * delete a departmentLimit from db
     * @param id
     */
    void removeDepartmentLimit(Short id);
}
