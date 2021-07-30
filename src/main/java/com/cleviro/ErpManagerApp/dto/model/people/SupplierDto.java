package com.cleviro.ErpManagerApp.dto.model.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SupplierDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String phone1;
    private String kraPin;
    private String postalAddress;
    private String state;
    private String city;
    private String status;
    private Date regDate;
    private String website;

    private CountryDto country;
    private UserDto user;
    private SupplierTypeDto supplierType;
}
