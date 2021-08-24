package com.cleviro.ErpManagerApp.service.patients;

import com.cleviro.ErpManagerApp.dto.model.patients.TriageDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;

import java.util.Collection;

public interface TriageService {
    /**
     * Get a triage by id
     * @param id
     */
    TriageDto findTriageById(Long id);

    /**
     * Get a triage by id
     * @param visitDto
     */
    TriageDto findTriageByVisit(VisitDto  visitDto);

    /**
     * Find all the triages
     * @param
     */
    Collection<TriageDto> findAllTriages();

    /**
     *
     * Create a new triage
     * @param triageDto
     */
    TriageDto addTriage(TriageDto triageDto);

    /**
     * update triage details
     * @param triageDto
     */
    TriageDto updateTriage(TriageDto triageDto);

    /**
     * delete a triage from db
     * @param id
     */
    void removeTriage(Long id);
}
