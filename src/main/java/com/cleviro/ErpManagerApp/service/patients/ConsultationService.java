package com.cleviro.ErpManagerApp.service.patients;

import com.cleviro.ErpManagerApp.dto.model.patients.ConsultationDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;

import java.util.Collection;

public interface ConsultationService {
    /**
     * Get a consultation by id
     * @param id
     */
    ConsultationDto findConsultationById(Long id);

    /**
     * Get a consultation by visitcode
     * @param visitDto
     */
    ConsultationDto findConsultationByVisit(VisitDto visitDto);


    /**
     * Find all the consultations
     * @param
     */
    Collection<ConsultationDto> findAllConsultations();

    /**
     *
     * Create a new consultation
     * @param consultationDto
     */
    ConsultationDto addConsultation(ConsultationDto consultationDto);

    /**
     * update consultation details
     * @param consultationDto
     */
    ConsultationDto updateConsultation(ConsultationDto consultationDto);

    /**
     * delete a consultation from db
     * @param id
     */
    void removeConsultation(Long id);
}
