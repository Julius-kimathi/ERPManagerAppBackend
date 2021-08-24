package com.cleviro.ErpManagerApp.dto.mapper.patients;

import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDto;
import com.cleviro.ErpManagerApp.dto.model.masters.*;
import com.cleviro.ErpManagerApp.dto.model.patients.ConsultationDto;
import com.cleviro.ErpManagerApp.dto.model.patients.SubVisitDto;
import com.cleviro.ErpManagerApp.dto.model.patients.TriageDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerDto;
import com.cleviro.ErpManagerApp.dto.model.people.DependentDto;
import com.cleviro.ErpManagerApp.model.patients.Visit;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class VisitMapper {
    public static VisitDto toVisitDto(Visit visit){
        return new VisitDto()
                .setId(visit.getId())
                .setVisitCode(visit.getVisitCode())
                .setVisitDate(visit.getVisitDate())
                .setBillType(visit.getBillType())
                .setAvailableLimit(visit.getAvailableLimit())
                .setOverallPaymentStatus(visit.getOverallPaymentStatus())
                .setCopay(visit.getCopay())
                .setInvoiceNo(visit.getInvoiceNo())
                .setInvoiceAmount(visit.getInvoiceAmount())
                .setTriage(new ModelMapper().map(visit.getTriage(), TriageDto.class))
                .setConsultation(new ModelMapper().map(visit.getConsultation(), ConsultationDto.class))
                .setPrincipal(new ModelMapper().map(visit.getPrincipal(), CustomerDto.class))
                .setDependent(new ModelMapper().map(visit.getDependent(), DependentDto.class))
                .setPayerAccount(new ModelMapper().map(visit.getPayerAccount(), PayerAccountDto.class))
                .setPlan(new ModelMapper().map(visit.getPlan(), PlanDto.class))
                .setConsultationRate(new ModelMapper().map(visit.getConsultationRate(), ConsultationRateDto.class))
                .setLocation(new ModelMapper().map(visit.getLocation(), LocationDto.class))
                .setCompany(new ModelMapper().map(visit.getCompany(), CompanyDto.class))
                .setDepartmentBills(new HashSet<>(visit.getDepartmentBills().stream().map(departmentBill -> new ModelMapper().map(departmentBill, DepartmentBillDto.class)).collect(Collectors.toSet())))
                .setSubVisits(new HashSet<>(visit.getSubVisits().stream().map(subVisit -> new ModelMapper().map(subVisit, SubVisitDto.class)).collect(Collectors.toSet())));
    }
}
