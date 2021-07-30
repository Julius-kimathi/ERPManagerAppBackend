package com.cleviro.ErpManagerApp.dto.model.masters;

import com.cleviro.ErpManagerApp.dto.model.people.CustomerDto;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.dto.model.people.SupplierDto;
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
public class CountryDto {
    private int id;
    private String name;
    private Short mobileCode;
    private String abbreviation;
    private String currency;
    private Byte currencyCode;
    private Short currencySymbol;

    private Set<CompanyDto> companies;
    private Set<SupplierDto> suppliers;
    private Set<CustomerDto> customers;
    private Set<EmployeeDto> employees;
}
