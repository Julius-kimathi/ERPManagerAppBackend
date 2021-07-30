package com.cleviro.ErpManagerApp.dto.model.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmploymentTypeDto {
    private Short id;
    private String name;
    private String description;

    private CompanyDto company;
    private Set<EmployeeDto> employees;
}
