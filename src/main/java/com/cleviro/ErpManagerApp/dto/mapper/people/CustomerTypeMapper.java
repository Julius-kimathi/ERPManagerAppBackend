package com.cleviro.ErpManagerApp.dto.mapper.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerDto;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerTypeDto;
import com.cleviro.ErpManagerApp.model.people.CustomerType;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class CustomerTypeMapper {
    public static CustomerTypeDto toCustomerTypeDto(CustomerType customerType){
        return new CustomerTypeDto()
                .setId(customerType.getId())
                .setName(customerType.getName())
                .setDescription(customerType.getDescription())
                .setCompany(new ModelMapper().map(customerType.getCompany(), CompanyDto.class))
                .setCustomers(new HashSet<CustomerDto>(customerType.getCustomers().stream().map(customer -> new ModelMapper().map(customer, CustomerDto.class)).collect(Collectors.toSet())));
    }
}
