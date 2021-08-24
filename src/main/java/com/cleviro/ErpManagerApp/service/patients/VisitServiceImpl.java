package com.cleviro.ErpManagerApp.service.patients;

import com.cleviro.ErpManagerApp.dto.mapper.patients.VisitMapper;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.*;
import com.cleviro.ErpManagerApp.model.patients.Consultation;
import com.cleviro.ErpManagerApp.model.patients.Triage;
import com.cleviro.ErpManagerApp.model.patients.Visit;
import com.cleviro.ErpManagerApp.model.people.Customer;
import com.cleviro.ErpManagerApp.model.people.Dependent;
import com.cleviro.ErpManagerApp.repository.patients.VisitRepository;
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
public class VisitServiceImpl implements VisitService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private VisitRepository visitRepository;

    @Override
    public VisitDto findVisitById(Long id) {
        Optional<Visit> visit = visitRepository.findById(id);
        if (visit.isPresent())
            return VisitMapper.toVisitDto(visit.get());
        else throw ExceptionUtil.exception(EntityType.VISIT, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public VisitDto findVisitByVisitCode(String visitCode) {
        Optional<Visit> visit = visitRepository.findByVisitCode(visitCode);
        if (visit.isPresent())
            return VisitMapper.toVisitDto(visit.get());
        else throw ExceptionUtil.exception(EntityType.VISIT, ExceptionType.ENTITY_NOT_FOUND,visitCode);
    }


    @Override
    public Collection<VisitDto> findAllVisits() {
        return StreamSupport.stream(visitRepository.findAll().spliterator(),false)
                .map(VisitMapper::toVisitDto)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public VisitDto addVisit(VisitDto visitDto) {
        Optional<Visit> visit = visitRepository.findByVisitCode(visitDto.getVisitCode());
        if (!visit.isPresent()){
            String visitCode = RandomStringUtil.getAlphaNumericString(6,EntityType.VISIT);
            Visit visitModel = new Visit()
                    .setVisitCode(visitCode)
                    .setVisitDate(LocalDateTime.now())
                    .setBillType(visitDto.getBillType())
                    .setAvailableLimit(visitDto.getAvailableLimit())
                    .setOverallPaymentStatus(visitDto.getOverallPaymentStatus())
                    .setCopay(visitDto.getCopay())
                    .setInvoiceNo(RandomStringUtil.generateInvoiceNo(visitDto.getCompany().getOrderPrefix()))
                    .setInvoiceAmount(visitDto.getInvoiceAmount())
                    .setTriage(modelMapper.map(visitDto.getTriage(), Triage.class))
                    .setConsultation(modelMapper.map(visitDto.getConsultation(), Consultation.class))
                    .setPrincipal(modelMapper.map(visitDto.getPrincipal(), Customer.class))
                    .setDependent(modelMapper.map(visitDto.getDependent(), Dependent.class))
                    .setPayerAccount(modelMapper.map(visitDto.getPayerAccount(), PayerAccount.class))
                    .setPlan(modelMapper.map(visitDto.getPlan(), Plan.class))
                    .setConsultationRate(modelMapper.map(visitDto.getConsultationRate(), ConsultationRate.class))
                    .setLocation(modelMapper.map(visitDto.getLocation(), Location.class))
                    .setCompany(modelMapper.map(visitDto.getCompany(), Company.class));
            return VisitMapper.toVisitDto(visitRepository.save(visitModel));
        }
         throw ExceptionUtil.exception(EntityType.VISIT, ExceptionType.DUPLICATE_ENTITY, visitDto.getVisitCode());
    }

    @Override
    public VisitDto updateVisit(VisitDto visitDto) {
        Optional<Visit> visit = visitRepository.findById(visitDto.getId());
        if (visit.isPresent()){
            Visit visitModel =visit.get()
                   // .setVisitDate(LocalDateTime.now())
                    .setBillType(visitDto.getBillType())
                    .setAvailableLimit(visitDto.getAvailableLimit())
                    .setOverallPaymentStatus(visitDto.getOverallPaymentStatus())
                    .setCopay(visitDto.getCopay())
                   // .setInvoiceNo(visitDto.getInvoiceNo())
                    .setInvoiceAmount(visitDto.getInvoiceAmount())
                    .setTriage(modelMapper.map(visitDto.getTriage(), Triage.class))
                    .setConsultation(modelMapper.map(visitDto.getConsultation(), Consultation.class))
                    .setPrincipal(modelMapper.map(visitDto.getPrincipal(), Customer.class))
                    .setDependent(modelMapper.map(visitDto.getDependent(), Dependent.class))
                    .setPayerAccount(modelMapper.map(visitDto.getPayerAccount(), PayerAccount.class))
                    .setPlan(modelMapper.map(visitDto.getPlan(), Plan.class))
                    .setConsultationRate(modelMapper.map(visitDto.getConsultationRate(), ConsultationRate.class))
                    .setLocation(modelMapper.map(visitDto.getLocation(), Location.class))
                    .setCompany(modelMapper.map(visitDto.getCompany(), Company.class));
            return VisitMapper.toVisitDto(visitRepository.save(visitModel));
        }
        throw ExceptionUtil.exception(EntityType.VISIT, ExceptionType.ENTITY_NOT_FOUND, visitDto.toString());
    }

    @Override
    public void removeVisit(Long id) {

        Optional<Visit> visit = visitRepository.findById(id);
        if (visit.isPresent())
            visitRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.VISIT, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
