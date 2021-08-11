package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.ConsultationTypeDto;

import java.util.Collection;

public interface ConsultationTypeService {
    /**
     * Get a consultationType by id
     * @param id
     */
    ConsultationTypeDto findConsultationTypeById(Short id);

    /**
     * Find all the consultationTypes
     * @param
     */
    Collection<ConsultationTypeDto> findAllConsultationTypes();

    /**
     *
     * Create a new consultationType
     * @param consultationTypeDto
     */
    ConsultationTypeDto addConsultationType(ConsultationTypeDto consultationTypeDto);

    /**
     * update consultationType details
     * @param consultationTypeDto
     */
    ConsultationTypeDto updateConsultationType(ConsultationTypeDto consultationTypeDto);

    /**
     * delete a consultationType from db
     * @param id
     */
    void removeConsultationType(Short id);
}
