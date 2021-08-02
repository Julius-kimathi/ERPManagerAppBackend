package com.cleviro.ErpManagerApp.dto.mapper.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.dto.model.people.EmploymentTypeDto;
import com.cleviro.ErpManagerApp.model.people.EmploymentType;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class EmploymentTypeMapper {
    public static EmploymentTypeDto toEmployeeTypeDto(EmploymentType employmentType){
        return new EmploymentTypeDto()
                .setId(employmentType.getId())
                .setName(employmentType.getName())
                .setDescription(employmentType.getDescription())
                .setCompany(new ModelMapper().map(employmentType.getCompany(), CompanyDto.class))
                .setEmployees(new HashSet<>(employmentType.getEmployees().stream().map(employee -> new ModelMapper().map(employee, EmployeeDto.class)).collect(Collectors.toSet())));

    }
}
