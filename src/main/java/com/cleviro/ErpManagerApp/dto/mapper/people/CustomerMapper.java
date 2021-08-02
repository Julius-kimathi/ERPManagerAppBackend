package com.cleviro.ErpManagerApp.dto.mapper.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerDto;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerTypeDto;
import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.cleviro.ErpManagerApp.model.people.Customer;
import org.modelmapper.ModelMapper;

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer){
        return new CustomerDto()
                .setId(customer.getId())
                .setFirstName(customer.getFirstName())
                .setMiddleName(customer.getMiddleName())
                .setLastName(customer.getLastName())
                .setDob(customer.getDob())
                .setEmail(customer.getEmail())
                .setPhone(customer.getPhone())
                .setPhone1(customer.getPhone1())
                .setIdNo(customer.getIdNo())
                .setPostalAddress(customer.getPostalAddress())
                .setCity(customer.getCity())
                .setState(customer.getState())
                .setStatus(customer.getStatus())
                .setRegDate(customer.getRegDate())
                .setGender(customer.getGender())
                .setUser(new ModelMapper().map(customer.getUser(), UserDto.class))
                .setCountry(new ModelMapper().map(customer.getCountry(), CountryDto.class))
                .setCustomerType(new ModelMapper().map(customer.getCustomerType(), CustomerTypeDto.class));
    }
}
