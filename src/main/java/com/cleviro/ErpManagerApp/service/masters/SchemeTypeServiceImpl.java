package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.SchemeTypeMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.SchemeTypeDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.SchemeType;
import com.cleviro.ErpManagerApp.repository.masters.SchemeTypeRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SchemeTypeServiceImpl implements SchemeTypeService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SchemeTypeRepository schemeTypeRepository;


    @Override
    public SchemeTypeDto findSchemeTypeById(Short id) {
        Optional<SchemeType> schemeType = schemeTypeRepository.findById(id);
        if (schemeType.isPresent())
            return SchemeTypeMapper.toSchemeTypeDto(schemeType.get());
        else
            throw ExceptionUtil.exception(EntityType.SCHEMETYPE, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public Collection<SchemeTypeDto> findAllSchemeTypes() {
        return StreamSupport.stream(schemeTypeRepository.findAll().spliterator(),false)
                .map(SchemeTypeMapper::toSchemeTypeDto)
                .collect(Collectors.toSet());
    }

    @Override
    public SchemeTypeDto addSchemeType(SchemeTypeDto schemeTypeDto) {
        Optional<SchemeType> schemeType = schemeTypeRepository.findByName(schemeTypeDto.getName());
        if (!schemeType.isPresent()){
            SchemeType schemeTypeModel = new SchemeType().setName(schemeTypeDto.getName());
            return SchemeTypeMapper.toSchemeTypeDto(schemeTypeRepository.save(schemeTypeModel));
        }
        throw ExceptionUtil.exception(EntityType.SCHEMETYPE, ExceptionType.DUPLICATE_ENTITY,schemeTypeDto.getName().toString());
    }

    @Override
    public SchemeTypeDto updateSchemeType(SchemeTypeDto schemeTypeDto) {
        Optional<SchemeType> schemeType = schemeTypeRepository.findById(schemeTypeDto.getId());
        if (schemeType.isPresent()){
            SchemeType schemeTypeModel = schemeType.get().setName(schemeTypeDto.getName());
            return SchemeTypeMapper.toSchemeTypeDto(schemeTypeRepository.save(schemeTypeModel));
        }
        throw ExceptionUtil.exception(EntityType.SCHEMETYPE, ExceptionType.ENTITY_NOT_FOUND,schemeTypeDto.getName().toString());
    }

    @Override
    public void removeSchemeType(Short id) {
        Optional<SchemeType> schemeType = schemeTypeRepository.findById(id);
        if (schemeType.isPresent()) schemeTypeRepository.deleteById(id);
        else
            throw ExceptionUtil.exception(EntityType.SCHEMETYPE, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
