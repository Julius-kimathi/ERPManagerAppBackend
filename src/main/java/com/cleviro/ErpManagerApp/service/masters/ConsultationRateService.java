package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.ConsultationRateDto;

import java.util.Collection;

public interface ConsultationRateService {
    /**
     * Get a ConsultationRate by id
     * @param id
     */
    ConsultationRateDto findConsultationRateById(int id);

    /**
     * Find all the consultationRates
     * @param
     */
    Collection<ConsultationRateDto> findAllConsultationRates();

    /**
     *
     * Create a new ConsultationRate
     * @param consultationRate
     */
    ConsultationRateDto addConsultationRate(ConsultationRateDto consultationRate);

    /**
     * update ConsultationRate details
     * @param consultationRate
     */
    ConsultationRateDto updateConsultationRate(ConsultationRateDto consultationRate);

    /**
     * delete a ConsultationRate from db
     * @param id
     */
    void removeConsultationRate(int id);
}
