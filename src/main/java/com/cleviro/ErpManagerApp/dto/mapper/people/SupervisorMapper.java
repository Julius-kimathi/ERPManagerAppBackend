package com.cleviro.ErpManagerApp.dto.mapper.people;

import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.dto.model.people.SupervisorDto;
import com.cleviro.ErpManagerApp.model.people.Supervisor;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class SupervisorMapper {
    public static SupervisorDto toSupervisorDto(Supervisor supervisor){
        return  new SupervisorDto()
                .setId(supervisor.getId())
                .setSupervisorDetails(EmployeeMapper.toEmployeeDto(supervisor.getSupervisorDetails()))
                .setSupervises(new HashSet<>(supervisor.getSupervises().stream().map(employee -> new ModelMapper().map(employee, EmployeeDto.class)).collect(Collectors.toSet())));
    }
}
