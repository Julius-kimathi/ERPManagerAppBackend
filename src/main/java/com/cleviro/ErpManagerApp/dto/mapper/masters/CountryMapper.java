package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerDto;
import com.cleviro.ErpManagerApp.dto.model.people.EmployeeDto;
import com.cleviro.ErpManagerApp.dto.model.people.SupplierDto;
import com.cleviro.ErpManagerApp.model.masters.Country;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class CountryMapper {
    public static CountryDto toCountryDto(Country country){
        return new CountryDto()
                .setId(country.getId())
                .setName(country.getName())
                .setMobileCode(country.getMobileCode())
                .setAbbreviation(country.getAbbreviation())
                .setCurrency(country.getCurrency())
                .setCurrencyCode(country.getCurrencyCode())
                .setCurrencySymbol(country.getCurrencySymbol())
                .setCompanies(new HashSet<CompanyDto>(country.getCompanies().stream().map(company -> new ModelMapper().map(company, CompanyDto.class)).collect(Collectors.toSet())))
                .setSuppliers(new HashSet<SupplierDto>(country.getSuppliers().stream().map(supplier -> new ModelMapper().map(supplier, SupplierDto.class)).collect(Collectors.toSet())))
                .setCustomers(new HashSet<CustomerDto>(country.getCustomers().stream().map(customer -> new ModelMapper().map(customer, CustomerDto.class)).collect(Collectors.toSet())))
                .setEmployees(new HashSet<EmployeeDto>(country.getEmployees().stream().map(employee -> new ModelMapper().map(employee, EmployeeDto.class)).collect(Collectors.toSet())));
    }
}
