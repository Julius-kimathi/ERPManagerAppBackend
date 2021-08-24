package com.cleviro.ErpManagerApp.service.patients;

import com.cleviro.ErpManagerApp.dto.mapper.patients.ConsultationMapper;
import com.cleviro.ErpManagerApp.dto.model.patients.ConsultationDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.patients.Consultation;
import com.cleviro.ErpManagerApp.model.patients.Visit;
import com.cleviro.ErpManagerApp.repository.patients.ConsultationRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ConsultationServiceImpl implements ConsultationService{
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ConsultationDto findConsultationById(Long id) {
        Optional<Consultation> consultation = consultationRepository.findById(id);
        if (consultation.isPresent())
            return ConsultationMapper.toConsultationDto(consultation.get());
        else throw ExceptionUtil.exception(EntityType.CONSULTATION, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public ConsultationDto findConsultationByVisit(VisitDto visitDto) {
        Optional<Consultation> consultation = consultationRepository.findByVisit(visitDto);
        if (consultation.isPresent())
            return ConsultationMapper.toConsultationDto(consultation.get());
        else throw ExceptionUtil.exception(EntityType.CONSULTATION, ExceptionType.ENTITY_NOT_FOUND,visitDto.toString());
    }

    @Override
    public Collection<ConsultationDto> findAllConsultations() {
        return StreamSupport.stream(consultationRepository.findAll().spliterator(),false)
                .map(ConsultationMapper::toConsultationDto)
                .collect(Collectors.toSet());
    }

    @Override
    public ConsultationDto addConsultation(ConsultationDto consultationDto) {
      Optional<Consultation> consultation = consultationRepository.findByVisit(consultationDto.getVisit());
      if (!consultation.isPresent()){
          Consultation consultationModel = new Consultation()
                  .setStatus(consultationDto.getStatus())
                  .setVisit(modelMapper.map(consultationDto.getVisit(), Visit.class));
          return ConsultationMapper.toConsultationDto(consultationRepository.save(consultationModel));
      }
        throw ExceptionUtil.exception(EntityType.CONSULTATION, ExceptionType.DUPLICATE_ENTITY,consultationDto.toString());
    }

    @Override
    public ConsultationDto updateConsultation(ConsultationDto consultationDto) {
        Optional<Consultation> consultation = consultationRepository.findById(consultationDto.getId());
        if (consultation.isPresent()){
            Consultation consultationModel = consultation.get()
                    .setStatus(consultationDto.getStatus())
                    .setVisit(modelMapper.map(consultationDto.getVisit(), Visit.class));
            return ConsultationMapper.toConsultationDto(consultationRepository.save(consultationModel));
        }
        throw ExceptionUtil.exception(EntityType.CONSULTATION, ExceptionType.ENTITY_NOT_FOUND,consultationDto.toString());
    }

    @Override
    public void removeConsultation(Long id) {
        Optional<Consultation> consultation = consultationRepository.findById(id);
        if (consultation.isPresent())
          consultationRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.CONSULTATION, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
