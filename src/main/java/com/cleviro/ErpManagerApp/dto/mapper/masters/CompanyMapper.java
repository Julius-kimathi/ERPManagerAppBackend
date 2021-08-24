package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.cleviro.ErpManagerApp.dto.model.masters.ZoneDto;
import com.cleviro.ErpManagerApp.dto.model.people.*;
import com.cleviro.ErpManagerApp.model.masters.Company;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class CompanyMapper {
   public static CompanyDto toCompanyDto(Company company){
       return new CompanyDto()
               .setId(company.getId())
               .setName(company.getName())
               .setPostalAddress(company.getPostalAddress())
               .setState(company.getState())
               .setCity(company.getCity())
               .setPhone(company.getPhone())
               .setPhone1(company.getPhone1())
               .setPhone2(company.getPhone2())
               .setEmail(company.getEmail())
               .setEmail1(company.getEmail1())
               .setEmail2(company.getEmail2())
               .setAbbreviation(company.getAbbreviation())
               .setWebsite(company.getWebsite())
               .setOrderPrefix(company.getOrderPrefix())
               .setCountry(new ModelMapper().map(company.getCountry(), CountryDto.class))
               .setLocations(new HashSet<LocationDto>(company.getLocations().stream().map(location -> new ModelMapper().map(location, LocationDto.class)).collect(Collectors.toSet())))
               .setZones(new HashSet<ZoneDto>(company.getZones().stream().map(zone -> new ModelMapper().map(zone,ZoneDto.class)).collect(Collectors.toSet())))
               .setDesignations(new HashSet<DesignationDto>(company.getDesignations().stream()
                       .map(designation -> new ModelMapper().map(designation, DesignationDto.class))
                       .collect(Collectors.toSet())))
               .setEmploymentTypes(new HashSet<EmploymentTypeDto>(company.getEmploymentTypes().stream()
                       .map(employmentType -> new ModelMapper().map(employmentType, EmploymentTypeDto.class))
                       .collect(Collectors.toSet())))
               .setSupplierTypes(new HashSet<SupplierTypeDto>(company.getSupplierTypes().stream().map(supplierType -> new ModelMapper()
                       .map(supplierType, SupplierTypeDto.class)).collect(Collectors.toSet())))
               .setEmployees(new HashSet<EmployeeDto>(company.getEmployees().stream()
                       .map(employee -> new ModelMapper().map(employee, EmployeeDto.class))
                       .collect(Collectors.toSet())));


   }
}
