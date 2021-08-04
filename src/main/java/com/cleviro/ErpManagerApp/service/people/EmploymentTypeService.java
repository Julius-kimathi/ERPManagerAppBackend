package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.model.people.EmploymentTypeDto;

import java.util.Collection;

public interface EmploymentTypeService {
    /**
     * Get a employmentType by id
     * @param id
     */
    EmploymentTypeDto findEmploymentTypeById(Short id);

    /**
     * Find all the employmentTypes
     * @param
     */
    Collection<EmploymentTypeDto> findAllEmploymentTypes();

    /**
     *
     * Create a new employmentType
     * @param employmentTypeDto
     */
    EmploymentTypeDto addEmploymentType(EmploymentTypeDto employmentTypeDto);

    /**
     * update employmentType details
     * @param employmentTypeDto
     */
    EmploymentTypeDto updateEmploymentType(EmploymentTypeDto employmentTypeDto);

    /**
     * delete a employmentType from db
     * @param id
     */
    void removeEmploymentType(Short id);
}
