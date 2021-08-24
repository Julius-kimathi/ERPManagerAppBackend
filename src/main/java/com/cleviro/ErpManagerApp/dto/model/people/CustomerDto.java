package com.cleviro.ErpManagerApp.dto.model.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
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
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {
    private Long id;
    private String customerCode;
    private String firstName;
    private String middleName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String phone;
    private String phone1;
    private String idNo;
    private String postalAddress;
    private String city;
    private String state;
    private String status;
    private LocalDate regDate;
    @Enumerated(EnumType.STRING)
    private Genders gender;

    private UserDto user;
    private CountryDto country;
    private CompanyDto company;
    private LocationDto location;
    private Set<VisitDto> visits;
    private Set<DependentDto> dependents;
}
