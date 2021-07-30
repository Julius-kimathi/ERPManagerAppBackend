package com.cleviro.ErpManagerApp.dto.model.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
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

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {
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
    @Enumerated(EnumType.STRING)
    private Genders gender;

    private UserDto user;
    private CountryDto country;
    private CustomerTypeDto customerType;
}
