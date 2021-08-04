package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.mapper.people.EmploymentTypeMapper;
import com.cleviro.ErpManagerApp.dto.model.people.EmploymentTypeDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.people.EmploymentType;
import com.cleviro.ErpManagerApp.repository.people.EmploymentTypeRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class EmploymentTypeServiceImpl implements  EmploymentTypeService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmploymentTypeRepository employmentTypeRepository;

    @Override
    public EmploymentTypeDto findEmploymentTypeById(Short id) {
        Optional<EmploymentType> employmentType = employmentTypeRepository.findById(id);
        if (employmentType.isPresent()) return EmploymentTypeMapper.toEmployeeTypeDto(employmentType.get());
        else throw ExceptionUtil.exception(EntityType.EMPLOYMENTTYPE, ExceptionType.ENTITY_NOT_FOUND, id.toString());
    }

    @Override
    public Collection<EmploymentTypeDto> findAllEmploymentTypes() {
        return StreamSupport.stream(employmentTypeRepository.findAll().spliterator(), false)
                .map(EmploymentTypeMapper::toEmployeeTypeDto)
                .collect(Collectors.toSet());
    }

    @Override
    public EmploymentTypeDto addEmploymentType(EmploymentTypeDto employmentTypeDto) {
        Optional<EmploymentType> employmentType = employmentTypeRepository.findByName(employmentTypeDto.getName());
        if (!employmentType.isPresent()){
            EmploymentType employmentTypeModel = new EmploymentType()
                    .setName(employmentTypeDto.getName())
                    .setDescription(employmentTypeDto.getDescription())
                    .setCompany(modelMapper.map(employmentTypeDto.getCompany(), Company.class));
            return EmploymentTypeMapper.toEmployeeTypeDto(employmentTypeRepository.save(employmentTypeModel));
        }
        throw ExceptionUtil.exception(EntityType.EMPLOYMENTTYPE, ExceptionType.DUPLICATE_ENTITY, employmentTypeDto.getName());
    }

    @Override
    public EmploymentTypeDto updateEmploymentType(EmploymentTypeDto employmentTypeDto) {
       Optional<EmploymentType> employmentType = employmentTypeRepository.findById(employmentTypeDto.getId());
       if (employmentType.isPresent()){
           EmploymentType employmentTypeModel = employmentType.get()
                   .setName(employmentTypeDto.getName())
                   .setDescription(employmentTypeDto.getDescription())
                   .setCompany(modelMapper.map(employmentTypeDto.getCompany(), Company.class));
           return EmploymentTypeMapper.toEmployeeTypeDto(employmentTypeRepository.save(employmentTypeModel));
       }
        throw ExceptionUtil.exception(EntityType.EMPLOYMENTTYPE, ExceptionType.ENTITY_NOT_FOUND, employmentTypeDto.getName());
    }

    @Override
    public void removeEmploymentType(Short id) {
        if (employmentTypeRepository.findById(id).isPresent()) employmentTypeRepository.deleteById(id);
        else
            throw ExceptionUtil.exception(EntityType.EMPLOYMENTTYPE, ExceptionType.ENTITY_NOT_FOUND, id.toString());
    }
}
