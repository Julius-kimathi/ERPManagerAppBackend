package com.cleviro.ErpManagerApp.dto.model.masters;

import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsultationRateDto {
    private int id;
    private BigDecimal fees;

    private ConsultationTypeDto consultationType;
    private EmployeeDto doctor;
    private LocationDto location;
    private SchemeDto scheme;
    private DepartmentDto department;
}
