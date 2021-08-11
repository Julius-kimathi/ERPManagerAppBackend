package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.ConsultationRateMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.ConsultationRateDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.*;
import com.cleviro.ErpManagerApp.model.people.Employee;
import com.cleviro.ErpManagerApp.repository.masters.ConsultationRateRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ConsultationRateServiceImpl implements ConsultationRateService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ConsultationRateRepository consultationRateRepository;

    @Override
    public ConsultationRateDto findConsultationRateById(int id) {
        Optional<ConsultationRate> consultationRate = consultationRateRepository.findById(id);
        if (consultationRate.isPresent())
            return ConsultationRateMapper.toConsultationRateDto(consultationRate.get());
        else throw ExceptionUtil.exception(EntityType.CONSULTATIONRATE, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }

    @Override
    public Collection<ConsultationRateDto> findAllConsultationRates() {
        return StreamSupport.stream(consultationRateRepository.findAll().spliterator(),false)
                .map(ConsultationRateMapper::toConsultationRateDto)
                .collect(Collectors.toSet());
    }

    @Override
    public ConsultationRateDto addConsultationRate(ConsultationRateDto consultationRateDto) {
        Optional<ConsultationRate> consultationRate = consultationRateRepository.findByConsultationTypeAndDoctorAndLocationAndSchemeAndDepartment(consultationRateDto.getConsultationType(),consultationRateDto.getDoctor(),consultationRateDto.getLocation(),consultationRateDto.getScheme(),consultationRateDto.getDepartment());
        if (!consultationRate.isPresent()){
           ConsultationRate consultationRateModel =  new ConsultationRate()
                   .setFees(consultationRateDto.getFees())
                   .setConsultationType(modelMapper.map(consultationRateDto.getConsultationType(), ConsultationType.class))
                   .setDoctor(modelMapper.map(consultationRateDto.getDoctor(), Employee.class))
                   .setLocation(modelMapper.map(consultationRateDto.getLocation(), Location.class))
                   .setScheme(modelMapper.map(consultationRateDto.getScheme(), Scheme.class))
                   .setDepartment(modelMapper.map(consultationRateDto.getDepartment(), Department.class));
           return ConsultationRateMapper.toConsultationRateDto(consultationRateRepository.save(consultationRateModel));
        }
        throw ExceptionUtil.exception(EntityType.CONSULTATIONRATE, ExceptionType.DUPLICATE_ENTITY,consultationRateDto.toString());
    }

    @Override
    public ConsultationRateDto updateConsultationRate(ConsultationRateDto consultationRateDto) {
        Optional<ConsultationRate> consultationRate = consultationRateRepository.findById(consultationRateDto.getId());
        if (consultationRate.isPresent()){
            ConsultationRate consultationRateModel =  consultationRate.get()
                    .setFees(consultationRateDto.getFees())
                    .setConsultationType(modelMapper.map(consultationRateDto.getConsultationType(), ConsultationType.class))
                    .setDoctor(modelMapper.map(consultationRateDto.getDoctor(), Employee.class))
                    .setLocation(modelMapper.map(consultationRateDto.getLocation(), Location.class))
                    .setScheme(modelMapper.map(consultationRateDto.getScheme(), Scheme.class))
                    .setDepartment(modelMapper.map(consultationRateDto.getDepartment(), Department.class));
            return ConsultationRateMapper.toConsultationRateDto(consultationRateRepository.save(consultationRateModel));
        }
        throw ExceptionUtil.exception(EntityType.CONSULTATIONRATE, ExceptionType.ENTITY_NOT_FOUND,consultationRateDto.toString());
    }

    @Override
    public void removeConsultationRate(int id) {
        Optional<ConsultationRate> consultationRate = consultationRateRepository.findById(id);
        if (consultationRate.isPresent()){
            consultationRateRepository.deleteById(id);
        }
        throw ExceptionUtil.exception(EntityType.CONSULTATIONRATE, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }
}
