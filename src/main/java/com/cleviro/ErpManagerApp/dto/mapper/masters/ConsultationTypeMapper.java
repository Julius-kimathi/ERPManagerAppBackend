package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.ConsultationRateDto;
import com.cleviro.ErpManagerApp.dto.model.masters.ConsultationTypeDto;
import com.cleviro.ErpManagerApp.model.masters.ConsultationType;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ConsultationTypeMapper {
    public static ConsultationTypeDto toConsultationTypeDto(ConsultationType consultationType){
        return new ConsultationTypeDto()
                .setId(consultationType.getId())
                .setName(consultationType.getName())
                .setConsultationRates(StreamSupport.stream(consultationType.getConsultationRates().spliterator(), false).map(consultationRate -> new ModelMapper().map(consultationRate, ConsultationRateDto.class)).collect(Collectors.toSet()));
    }
}
