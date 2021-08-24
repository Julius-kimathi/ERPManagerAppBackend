package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.model.people.DependentDto;

import java.util.Collection;

public interface DependentService {
    /**
     * Get a dependent by id
     * @param id
     */
    DependentDto findDependentById(Long id);

    /**
     * Get a dependent by id
     * @param idNo
     */
    DependentDto findDependentByIdNo(String idNo);
    /**
     * Get a dependent by id
     * @param email
     */
    DependentDto findDependentByEmail(String email);

    /**
     * Find all the dependents
     * @param
     */
    Collection<DependentDto> findAllDependents();

    /**
     *
     * Create a new dependent
     * @param dependentDto
     */
    DependentDto addDependent(DependentDto dependentDto);

    /**
     * update dependent details
     * @param dependentDto
     */
    DependentDto updateDependent(DependentDto dependentDto);

    /**
     * delete a dependent from db
     * @param id
     */
    void removeDependent(Long id);
}
