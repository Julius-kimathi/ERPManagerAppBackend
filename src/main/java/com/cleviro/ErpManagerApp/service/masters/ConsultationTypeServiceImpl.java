package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.ConsultationTypeMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.ConsultationTypeDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.ConsultationType;
import com.cleviro.ErpManagerApp.repository.masters.ConsultationTypeRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ConsultationTypeServiceImpl implements ConsultationTypeService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ConsultationTypeRepository consultationTypeRepository;

    @Override
    public ConsultationTypeDto findConsultationTypeById(Short id) {
        Optional<ConsultationType> consultationType = consultationTypeRepository.findById(id);
        if (consultationType.isPresent())
            return ConsultationTypeMapper.toConsultationTypeDto(consultationType.get());
            else
                throw ExceptionUtil.exception(EntityType.CONSULTATIONTYPE, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public Collection<ConsultationTypeDto> findAllConsultationTypes() {
        return StreamSupport.stream(consultationTypeRepository.findAll().spliterator(),false)
                .map(ConsultationTypeMapper::toConsultationTypeDto)
                .collect(Collectors.toSet());
    }

    @Override
    public ConsultationTypeDto addConsultationType(ConsultationTypeDto consultationTypeDto) {
        Optional<ConsultationType> consultationType = consultationTypeRepository.findByName(consultationTypeDto.getName());
        if (!consultationType.isPresent()){
            ConsultationType consultationTypeModel = new ConsultationType()
                    .setName(consultationTypeDto.getName());
            return ConsultationTypeMapper.toConsultationTypeDto(consultationTypeRepository.save(consultationTypeModel));

        }
        throw ExceptionUtil.exception(EntityType.CONSULTATIONTYPE, ExceptionType.DUPLICATE_ENTITY,consultationTypeDto.getName());
    }

    @Override
    public ConsultationTypeDto updateConsultationType(ConsultationTypeDto consultationTypeDto) {
        Optional<ConsultationType> consultationType = consultationTypeRepository.findById(consultationTypeDto.getId());
        if (consultationType.isPresent()){
            ConsultationType consultationTypeModel = consultationType.get()
                    .setName(consultationTypeDto.getName());
            return ConsultationTypeMapper.toConsultationTypeDto(consultationTypeRepository.save(consultationTypeModel));

        }
        throw ExceptionUtil.exception(EntityType.CONSULTATIONTYPE, ExceptionType.ENTITY_NOT_FOUND,consultationTypeDto.getName());
    }

    @Override
    public void removeConsultationType(Short id) {
        Optional<ConsultationType> consultationType = consultationTypeRepository.findById(id);
        if (consultationType.isPresent())
            consultationTypeRepository.deleteById(id);
        else
            throw ExceptionUtil.exception(EntityType.CONSULTATIONTYPE, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
