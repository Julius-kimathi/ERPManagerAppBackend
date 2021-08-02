package com.cleviro.ErpManagerApp.dto.mapper.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;
import com.cleviro.ErpManagerApp.dto.model.people.SupplierDto;
import com.cleviro.ErpManagerApp.dto.model.people.SupplierTypeDto;
import com.cleviro.ErpManagerApp.dto.model.people.UserDto;
import com.cleviro.ErpManagerApp.model.people.Supplier;
import org.modelmapper.ModelMapper;

public class SupplierMapper {
    public static SupplierDto toSupplierDto(Supplier supplier){
        return new SupplierDto()
                .setId(supplier.getId())
                .setName(supplier.getName())
                .setEmail(supplier.getEmail())
                .setPhone(supplier.getPhone())
                .setPhone1(supplier.getPhone1())
                .setKraPin(supplier.getKraPin())
                .setPostalAddress(supplier.getPostalAddress())
                .setState(supplier.getState())
                .setCity(supplier.getCity())
                .setStatus(supplier.getStatus())
                .setRegDate(supplier.getRegDate())
                .setWebsite(supplier.getWebsite())
                .setCountry(new ModelMapper().map(supplier.getCountry(), CountryDto.class))
                .setUser(new ModelMapper().map(supplier.getUser(), UserDto.class))
                .setSupplierType(new ModelMapper().map(supplier.getSupplierType(), SupplierTypeDto.class));
    }
}
