package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.cleviro.ErpManagerApp.model.masters.Department;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class DepartmentMapper {
    public static DepartmentDto toDepartmentDto(Department department){
        return new DepartmentDto()
                .setId(department.getId())
                .setName(department.getName())
                .setDescription(department.getDescription())
                .setEmployees(new HashSet<EmployeeDto>(department.getEmployees().stream().map(employee -> new ModelMapper().map(employee, EmployeeDto.class)).collect(Collectors.toSet())))
                .setCompany(new ModelMapper().map(department.getCompany(), CompanyDto.class))
                .setUsers(new HashSet<UserDto>(department.getUsers().stream().map(user -> new ModelMapper().map(user, UserDto.class)).collect(Collectors.toSet())));
    }
}
