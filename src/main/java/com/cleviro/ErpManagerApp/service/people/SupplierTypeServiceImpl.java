package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.mapper.people.SupplierTypeMapper;
import com.cleviro.ErpManagerApp.dto.model.people.SupplierTypeDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.people.SupplierType;
import com.cleviro.ErpManagerApp.repository.people.SupplierTypeRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SupplierTypeServiceImpl  implements SupplierTypeService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private SupplierTypeRepository supplierTypeRepository;

    @Override
    public SupplierTypeDto findSupplierTypeById(Short id) {
        Optional<SupplierType> supplierType = supplierTypeRepository.findById(id);
        if (supplierType.isPresent())
            return SupplierTypeMapper.toSupplierTypeDto(supplierType.get());
        else
            throw ExceptionUtil.exception(EntityType.SUPPLIERTYPE, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public Collection<SupplierTypeDto> findAllSupplierTypes() {
        return StreamSupport.stream(supplierTypeRepository.findAll().spliterator(), false)
                .map(SupplierTypeMapper::toSupplierTypeDto)
                .collect(Collectors.toSet());
    }

    @Override
    public SupplierTypeDto addSupplierType(SupplierTypeDto supplierTypeDto) {
        Optional<SupplierType> supplierType = supplierTypeRepository.findByName(supplierTypeDto.getName());
        if (!supplierType.isPresent()){
            SupplierType supplierTypeModel = new SupplierType()
                    .setName(supplierTypeDto.getName())
                    .setDescription(supplierTypeDto.getDescription())
                    .setCompany(modelMapper.map(supplierTypeDto.getCompany(), Company.class));
            return SupplierTypeMapper.toSupplierTypeDto(supplierTypeRepository.save(supplierTypeModel));
        }
        throw ExceptionUtil.exception(EntityType.SUPPLIERTYPE,ExceptionType.DUPLICATE_ENTITY,supplierTypeDto.getName());
    }

    @Override
    public SupplierTypeDto updateSupplierType(SupplierTypeDto supplierTypeDto) {
        Optional<SupplierType> supplierType = supplierTypeRepository.findById(supplierTypeDto.getId());
        if (supplierType.isPresent()){
            SupplierType supplierTypeModel = supplierType.get()
                    .setName(supplierTypeDto.getName())
                    .setDescription(supplierTypeDto.getDescription())
                    .setCompany(modelMapper.map(supplierTypeDto.getCompany(), Company.class));
            return SupplierTypeMapper.toSupplierTypeDto(supplierTypeRepository.save(supplierTypeModel));
        }
        throw ExceptionUtil.exception(EntityType.SUPPLIERTYPE,ExceptionType.ENTITY_NOT_FOUND,supplierTypeDto.getId().toString());
    }

    @Override
    public void removeSupplierType(Short id) {
        if (supplierTypeRepository.findById(id).isPresent()) supplierTypeRepository.deleteById(id);
        else  throw ExceptionUtil.exception(EntityType.SUPPLIERTYPE,ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
