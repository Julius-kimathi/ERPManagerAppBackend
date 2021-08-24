package com.cleviro.ErpManagerApp.dto.mapper.patients;

import com.cleviro.ErpManagerApp.dto.model.patients.TriageDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.model.patients.Triage;
import org.modelmapper.ModelMapper;

public class TriageMapper {
    public static TriageDto toTriageDto(Triage triage){
        return new TriageDto()
                .setId(triage.getId())
                .setStatus(triage.getStatus())
                .setVisit(new ModelMapper().map(triage.getVisit(), VisitDto.class));
    }
}
