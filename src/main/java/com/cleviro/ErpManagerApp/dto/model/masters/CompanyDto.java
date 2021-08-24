package com.cleviro.ErpManagerApp.dto.model.masters;

import com.cleviro.ErpManagerApp.dto.model.people.*;
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
public class CompanyDto {
    private int id;
    private String name;
    private String postalAddress;
    private String state;
    private String city;
    private String phone;
    private String phone1;
    private String phone2;
    private String email;
    private String email1;
    private String email2;
    private String abbreviation;
    private String website;
    private String orderPrefix;

    private CountryDto country;
    private Set<LocationDto> locations;
    private Set<ZoneDto> zones;
    private Set<DesignationDto> designations; //CEO,ACCOUNTANTS,PROGRAMMERS,DOC,NURSE,SPECIALIST,RECEPTIONISTS,
    private Set<EmploymentTypeDto> employmentTypes; //PERMANENT,CONTRACT,COMMISSION
    private Set<SupplierTypeDto> supplierTypes;
    private Set<EmployeeDto> employees;
}
