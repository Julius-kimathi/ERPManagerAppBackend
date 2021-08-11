package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.*;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.model.masters.ConsultationRate;
import org.modelmapper.ModelMapper;

public class ConsultationRateMapper {
    public static ConsultationRateDto toConsultationRateDto(ConsultationRate consultationRate){
        return new ConsultationRateDto()
                .setId(consultationRate.getId())
                .setFees(consultationRate.getFees())
                .setConsultationType(new ModelMapper().map(consultationRate.getConsultationType(), ConsultationTypeDto.class))
                .setDoctor(new ModelMapper().map(consultationRate.getDoctor(), EmployeeDto.class))
                .setLocation(new ModelMapper().map(consultationRate.getLocation(), LocationDto.class))
                .setScheme(new ModelMapper().map(consultationRate.getScheme(), SchemeDto.class))
                .setDepartment(new ModelMapper().map(consultationRate.getDepartment(), DepartmentDto.class));
    }
}
