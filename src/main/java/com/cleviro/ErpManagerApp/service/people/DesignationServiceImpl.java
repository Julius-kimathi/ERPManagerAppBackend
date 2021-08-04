package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.mapper.people.DesignationMapper;
import com.cleviro.ErpManagerApp.dto.model.people.DesignationDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.people.Designation;
import com.cleviro.ErpManagerApp.repository.people.DesignationRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class DesignationServiceImpl implements DesignationService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DesignationRepository designationRepository;

    @Override
    public DesignationDto findDesignationById(Short id) {
        Optional<Designation> designation = designationRepository.findById(id);
        if (designation.isPresent()) return modelMapper.map(designation.get(), DesignationDto.class);
        else throw ExceptionUtil.exception(EntityType.DESIGNATION, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public Collection<DesignationDto> findAllDesignations() {
        return StreamSupport.stream(designationRepository.findAll().spliterator(), false)
                .map(DesignationMapper::toDesignationDto)
                .collect(Collectors.toSet());
    }

    @Override
    public DesignationDto addDesignation(DesignationDto designationDto) {
        if (!Optional.ofNullable(designationRepository.findByName(designationDto.getName())).isPresent())
        {
            Designation designationModel = new Designation()
                    .setName(designationDto.getName())
                    .setDescription(designationDto.getDescription())
                    .setCompany(modelMapper.map(designationDto.getCompany(), Company.class));
            return DesignationMapper.toDesignationDto(designationRepository.save(designationModel));
        }
        throw ExceptionUtil.exception(EntityType.DESIGNATION,ExceptionType.DUPLICATE_ENTITY,designationDto.getName());
    }

    @Override
    public DesignationDto updateDesignation(DesignationDto designationDto) {
        Optional<Designation> designation = designationRepository.findById(designationDto.getId());
        if (designation.isPresent()){
            Designation designationModel = designation.get()
                    .setName(designationDto.getName())
                    .setDescription(designationDto.getDescription())
                    .setCompany(modelMapper.map(designationDto.getCompany(), Company.class));
            return DesignationMapper.toDesignationDto(designationRepository.save(designationModel));
        }
        throw ExceptionUtil.exception(EntityType.DESIGNATION,ExceptionType.ENTITY_NOT_FOUND,String.valueOf(designationDto.getId()));
    }

    @Override
    public void removeDesignation(Short id) {
        if (designationRepository.findById(id).isPresent()) designationRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.DESIGNATION,ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
