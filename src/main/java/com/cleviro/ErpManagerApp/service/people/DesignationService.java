package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.model.people.DesignationDto;

import java.util.Collection;

public interface DesignationService {
    /**
     * Get a designation by id
     * @param id
     */
    DesignationDto findDesignationById(Short id);

    /**
     * Find all the designations
     * @param
     */
    Collection<DesignationDto> findAllDesignations();

    /**
     *
     * Create a new designation
     * @param designationDto
     */
    DesignationDto addDesignation(DesignationDto designationDto);

    /**
     * update designation details
     * @param designationDto
     */
    DesignationDto updateDesignation(DesignationDto designationDto);

    /**
     * delete a designation from db
     * @param id
     */
    void removeDesignation(Short id);
}
