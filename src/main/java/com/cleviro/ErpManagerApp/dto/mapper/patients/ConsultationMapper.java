package com.cleviro.ErpManagerApp.dto.mapper.patients;

import com.cleviro.ErpManagerApp.dto.model.patients.ConsultationDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.model.patients.Consultation;
import org.modelmapper.ModelMapper;

public class ConsultationMapper {
    public static ConsultationDto toConsultationDto(Consultation consultation){
        return new ConsultationDto()
                .setId(consultation.getId())
                .setStatus(consultation.getStatus())
                .setVisit(new ModelMapper().map(consultation.getVisit(), VisitDto.class));
    }
}
