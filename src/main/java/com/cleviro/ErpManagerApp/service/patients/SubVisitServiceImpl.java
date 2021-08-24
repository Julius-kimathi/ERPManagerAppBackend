package com.cleviro.ErpManagerApp.service.patients;

import com.cleviro.ErpManagerApp.dto.mapper.patients.SubVisitMapper;
import com.cleviro.ErpManagerApp.dto.model.patients.SubVisitDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Location;
import com.cleviro.ErpManagerApp.model.patients.SubVisit;
import com.cleviro.ErpManagerApp.repository.patients.SubVisitRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import com.cleviro.ErpManagerApp.util.RandomStringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class SubVisitServiceImpl implements SubVisitService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SubVisitRepository subVisitRepository;

    @Override
    public SubVisitDto findSubVisitById(Long id) {
        Optional<SubVisit> subVisit = subVisitRepository.findById(id);
        if (subVisit.isPresent())
            return SubVisitMapper.toSubVisitDto(subVisit.get());
            else
                throw ExceptionUtil.exception(EntityType.SUBVISIT, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public SubVisitDto findSubVisitBySubVisitCode(String subVisitCode) {
        Optional<SubVisit> subVisit = subVisitRepository.findBySubVisitCode(subVisitCode);
        if (subVisit.isPresent())
            return SubVisitMapper.toSubVisitDto(subVisit.get());
        else
            throw ExceptionUtil.exception(EntityType.SUBVISIT, ExceptionType.ENTITY_NOT_FOUND,subVisitCode);
    }

    @Override
    public Collection<SubVisitDto> findAllSubVisits() {
        return StreamSupport.stream(subVisitRepository.findAll().spliterator(),false)
                .map(SubVisitMapper::toSubVisitDto)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public SubVisitDto addSubVisit(SubVisitDto subVisitDto) {
        Optional<SubVisit> subVisit = subVisitRepository.findBySubVisitCode(subVisitDto.getSubVisitCode());
        if (!subVisit.isPresent()){
            String subVisitCode = RandomStringUtil.getAlphaNumericString(6,EntityType.SUBVISIT);
            SubVisit subVisitModel = new SubVisit()
                    .setSubVisitCode(subVisitCode)
                    .setSubVisitDate(LocalDateTime.now())
                    .setAvailableLimit(subVisitDto.getAvailableLimit())
                    .setOverallPaymentStatus(subVisitDto.getOverallPaymentStatus())
                    .setCopay(subVisitDto.getCopay())
                    .setInvoiceNo(RandomStringUtil.generateInvoiceNo(subVisitDto.getLocation().getCompany().getOrderPrefix()))
                    .setInvoiceAmount(subVisitDto.getInvoiceAmount())
                    .setLocation(modelMapper.map(subVisitDto.getLocation(), Location.class));
            return SubVisitMapper.toSubVisitDto(subVisitRepository.save(subVisitModel));

        }
        throw ExceptionUtil.exception(EntityType.SUBVISIT, ExceptionType.DUPLICATE_ENTITY, subVisitDto.getSubVisitCode());
    }

    @Override
    public SubVisitDto updateSubVisit(SubVisitDto subVisitDto) {
        Optional<SubVisit> subVisit = subVisitRepository.findById(subVisitDto.getId());
        if (subVisit.isPresent()){
            SubVisit subVisitModel = subVisit.get()
                    //.setSubVisitDate(LocalDateTime.now())
                    .setAvailableLimit(subVisitDto.getAvailableLimit())
                    .setOverallPaymentStatus(subVisitDto.getOverallPaymentStatus())
                    .setCopay(subVisitDto.getCopay())
                   // .setInvoiceNo(subVisitDto.getInvoiceNo())
                    .setInvoiceAmount(subVisitDto.getInvoiceAmount())
                    .setLocation(modelMapper.map(subVisitDto.getLocation(), Location.class));
            return SubVisitMapper.toSubVisitDto(subVisitRepository.save(subVisitModel));

        }
        throw ExceptionUtil.exception(EntityType.SUBVISIT, ExceptionType.ENTITY_NOT_FOUND, subVisitDto.toString());
    }

    @Override
    public void removeSubVisit(Long id) {
        Optional<SubVisit> subVisit = subVisitRepository.findById(id);
        if (subVisit.isPresent())
           subVisitRepository.deleteById(id);
        else
            throw ExceptionUtil.exception(EntityType.SUBVISIT, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
