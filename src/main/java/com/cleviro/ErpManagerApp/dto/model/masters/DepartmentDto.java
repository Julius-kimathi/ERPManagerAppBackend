package com.cleviro.ErpManagerApp.dto.model.masters;

import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
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
public class DepartmentDto {
    private Short id;
    private String name;
    private String description;

    private Set<EmployeeDto> employees;
    private CompanyDto company;
    private Set<UserDto> users;  //All Users who have access to a department
}
