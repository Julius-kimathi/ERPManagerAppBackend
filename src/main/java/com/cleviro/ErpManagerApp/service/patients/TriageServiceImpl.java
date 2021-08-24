package com.cleviro.ErpManagerApp.service.patients;

import com.cleviro.ErpManagerApp.dto.mapper.patients.TriageMapper;
import com.cleviro.ErpManagerApp.dto.model.patients.TriageDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.patients.Triage;
import com.cleviro.ErpManagerApp.model.patients.Visit;
import com.cleviro.ErpManagerApp.repository.patients.TriageRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class TriageServiceImpl implements TriageService{
    @Autowired
    private TriageRepository triageRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TriageDto findTriageById(Long id) {
        Optional<Triage> triage = triageRepository.findById(id);
        if (triage.isPresent())
            return TriageMapper.toTriageDto(triage.get());
        else throw ExceptionUtil.exception(EntityType.TRIAGE, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public TriageDto findTriageByVisit(VisitDto visitDto) {
        Optional<Triage> triage = triageRepository.findByVisit(visitDto);
        if (triage.isPresent())
            return TriageMapper.toTriageDto(triage.get());
        else throw ExceptionUtil.exception(EntityType.TRIAGE, ExceptionType.ENTITY_NOT_FOUND,visitDto.toString());
    }

    @Override
    public Collection<TriageDto> findAllTriages() {
        return StreamSupport.stream(triageRepository.findAll().spliterator(),false)
                .map(TriageMapper::toTriageDto)
                .collect(Collectors.toSet());
    }

    @Override
    public TriageDto addTriage(TriageDto triageDto) {
       Optional<Triage> triage = triageRepository.findByVisit(triageDto.getVisit());
       if (!triage.isPresent()){
           Triage triageModel = new Triage()
                   .setStatus(triageDto.getStatus())
                   .setVisit(modelMapper.map(triageDto.getVisit(), Visit.class));
           return TriageMapper.toTriageDto(triageRepository.save(triageModel));
       }
        throw ExceptionUtil.exception(EntityType.TRIAGE, ExceptionType.DUPLICATE_ENTITY,triageDto.toString());
    }

    @Override
    public TriageDto updateTriage(TriageDto triageDto) {
        Optional<Triage> triage = triageRepository.findById(triageDto.getId());
        if (triage.isPresent()){
            Triage triageModel = triage.get()
                    .setStatus(triageDto.getStatus())
                    .setVisit(modelMapper.map(triageDto.getVisit(), Visit.class));
            return TriageMapper.toTriageDto(triageRepository.save(triageModel));
        }
        throw ExceptionUtil.exception(EntityType.TRIAGE, ExceptionType.ENTITY_NOT_FOUND,triageDto.toString());
    }

    @Override
    public void removeTriage(Long id) {
        Optional<Triage> triage = triageRepository.findById(id);
        if (triage.isPresent())
            triageRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.TRIAGE, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
