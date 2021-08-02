package com.cleviro.ErpManagerApp.dto.mapper.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.dto.model.people.DesignationDto;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.dto.model.people.EmploymentTypeDto;
import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.cleviro.ErpManagerApp.model.people.Employee;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class EmployeeMapper {
    public static EmployeeDto toEmployeeDto(Employee employee){
        return new EmployeeDto()
                .setId(employee.getId())
                .setFirstName(employee.getFirstName())
                .setMiddleName(employee.getMiddleName())
                .setLastName(employee.getLastName())
                .setDob(employee.getDob())
                .setEmail(employee.getEmail())
                .setPhone(employee.getPhone())
                .setPhone1(employee.getPhone1())
                .setIdNo(employee.getIdNo())
                .setPostalAddress(employee.getPostalAddress())
                .setCity(employee.getCity())
                .setState(employee.getState())
                .setStatus(employee.getStatus())
                .setRegDate(employee.getRegDate())
                .setGender(employee.getGender())
                .setValidityDate(employee.getValidityDate())
                .setPayrollNo(employee.getPayrollNo())
                .setUser(new ModelMapper().map(employee.getUser(), UserDto.class))
                .setCompany(new ModelMapper().map(employee.getCompany(), CompanyDto.class))
                .setCountry(new ModelMapper().map(employee.getCountry(), CountryDto.class))
                .setLocation(new ModelMapper().map(employee.getLocation(), LocationDto.class))
                .setDesignation(new ModelMapper().map(employee.getDesignation(), DesignationDto.class))
                .setEmploymentType(new ModelMapper().map(employee.getEmploymentType(), EmploymentTypeDto.class))
                .setSupervisor(new ModelMapper().map(employee.getSupervisor(), EmployeeDto.class))
                .setDepartment(new ModelMapper().map(employee.getDepartment(), DepartmentDto.class))
                .setSupervised(new HashSet<>(employee.getSupervised().stream().map(emp -> new ModelMapper().map(emp, EmployeeDto.class)).collect(Collectors.toSet())));
    }
}
