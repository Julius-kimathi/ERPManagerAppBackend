package com.cleviro.ErpManagerApp.service.patients;

import com.cleviro.ErpManagerApp.dto.model.patients.SubVisitDto;

import java.util.Collection;

public interface SubVisitService {
    /**
     * Get a subVisit by id
     * @param id
     */
    SubVisitDto findSubVisitById(Long id);

    /**
     * Get a subVisit by id
     * @param subVisitCode
     */
    SubVisitDto findSubVisitBySubVisitCode(String subVisitCode);


    /**
     * Find all the subVisits
     * @param
     */
    Collection<SubVisitDto> findAllSubVisits();

    /**
     *
     * Create a new subVisit
     * @param subVisitDto
     */
    SubVisitDto addSubVisit(SubVisitDto subVisitDto);

    /**
     * update subVisit details
     * @param subVisitDto
     */
    SubVisitDto updateSubVisit(SubVisitDto subVisitDto);

    /**
     * delete a subVisit from db
     * @param id
     */
    void removeSubVisit(Long id);
}
