package com.cleviro.ErpManagerApp.service.patients;

import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;

import java.util.Collection;

public interface VisitService {
    /**
     * Get a visit by id
     * @param id
     */
    VisitDto findVisitById(Long id);

    /**
     * Get a visit by id
     * @param visitCode
     */
    VisitDto findVisitByVisitCode(String visitCode);


    /**
     * Find all the visits
     * @param
     */
    Collection<VisitDto> findAllVisits();

    /**
     *
     * Create a new visit
     * @param visitDto
     */
    VisitDto addVisit(VisitDto visitDto);

    /**
     * update visit details
     * @param visitDto
     */
    VisitDto updateVisit(VisitDto visitDto);

    /**
     * delete a visit from db
     * @param id
     */
    void removeVisit(Long id);
}
