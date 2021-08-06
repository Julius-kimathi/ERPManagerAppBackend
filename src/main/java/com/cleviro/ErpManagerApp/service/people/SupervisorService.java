package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.model.people.SupervisorDto;

import java.util.Collection;

public interface SupervisorService {
    /**
     * Get a supervisor by id
     * @param id
     */
    SupervisorDto findSupervisorById(Long id);


    /**
     * Find all the supervisors
     * @param
     */
    Collection<SupervisorDto> findAllSupervisors();

    /**
     *
     * Create a new supervisor
     * @param supervisorDto
     */
    SupervisorDto addSupervisor(SupervisorDto supervisorDto);

    /**
     * update supervisor details
     * @param supervisorDto
     */
    SupervisorDto updateSupervisor(SupervisorDto supervisorDto);

    /**
     * delete a supervisor from db
     * @param id
     */
    void removeSupervisor(Long id);

}
