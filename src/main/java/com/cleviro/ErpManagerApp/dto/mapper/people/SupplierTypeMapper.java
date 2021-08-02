package com.cleviro.ErpManagerApp.dto.mapper.people;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.dto.model.people.SupplierDto;
import com.cleviro.ErpManagerApp.dto.model.people.SupplierTypeDto;
import com.cleviro.ErpManagerApp.model.people.SupplierType;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class SupplierTypeMapper
{
    public static SupplierTypeDto toSupplierTypeDto(SupplierType supplierType)
    {
        return new SupplierTypeDto()
                .setId(supplierType.getId())
                .setName(supplierType.getName())
                .setDescription(supplierType.getDescription())
                .setCompany(new ModelMapper().map(supplierType.getCompany(), CompanyDto.class))
                .setSuppliers(new HashSet<>(supplierType.getSuppliers().stream().map(supplier -> new ModelMapper().map(supplier, SupplierDto.class)).collect(Collectors.toSet())));
    }
}
