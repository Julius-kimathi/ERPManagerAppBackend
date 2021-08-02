package com.cleviro.ErpManagerApp.dto.mapper.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.people.DesignationDto;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.model.people.Designation;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class DesignationMapper {
    public static DesignationDto toDesignationDto(Designation designation){
        return new DesignationDto()
                .setId(designation.getId())
                .setName(designation.getName())
                .setDescription(designation.getDescription())
                .setCompany(new ModelMapper().map(designation.getCompany(), CompanyDto.class))
                .setEmployees(new HashSet<EmployeeDto>(designation.getEmployees().stream().map(employee -> new ModelMapper().map(employee, EmployeeDto.class)).collect(Collectors.toSet())));
    }
}
