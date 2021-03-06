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
public class LocationDto {
    private int id;
    private String name;
    private String abbreviation;
    private String postalAddress;
    private String state;
    private String city;
    private String phone;
    private String phone1;
    private String email;
    private String email1;
    private String status;


    private CompanyDto company;
    private ZoneDto zone;
    private Set<StoreDto> stores;
    private Set<EmployeeDto> employees;
    private Set<UserDto> users;  //All Users who have access to a location
}
