package com.cleviro.ErpManagerApp.dto.model.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.model.people.Genders;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private Date dob;
    private String email;
    private String phone;
    private String phone1;
    private String idNo;
    private String postalAddress;
    private String city;
    private String state;
    private String status;
    private Date regDate;
    private Date validityDate;
    private String payrollNo;
    @Enumerated(EnumType.STRING)
    private Genders gender;

    private UserDto user;
    private CompanyDto company;
    private CountryDto country;
    private LocationDto location;
    private DesignationDto designation;
    private EmploymentTypeDto employmentType;
    private EmployeeDto supervisor;
    private DepartmentDto department;
    private Set<EmployeeDto> supervised;
}
