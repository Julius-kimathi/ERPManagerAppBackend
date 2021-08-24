package com.cleviro.ErpManagerApp.dto.model.patients;

import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDto;
import com.cleviro.ErpManagerApp.dto.model.masters.*;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerDto;
import com.cleviro.ErpManagerApp.dto.model.people.DependentDto;
import com.cleviro.ErpManagerApp.model.masters.enums.BillTypes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VisitDto {
    private Long id;
    private String visitCode;
    private LocalDateTime visitDate;
    @Enumerated(EnumType.STRING)
    private BillTypes billType;
    private BigDecimal availableLimit;
    private Boolean overallPaymentStatus;
    private BigDecimal copay;
    private String invoiceNo;
    private BigDecimal invoiceAmount;

    private TriageDto triage;
    private ConsultationDto consultation;
    private CustomerDto principal;
    private DependentDto dependent;
    private PayerAccountDto payerAccount;
    private PlanDto plan;
    private ConsultationRateDto consultationRate;
    private LocationDto location;
    private CompanyDto company;
    private Set<DepartmentBillDto> departmentBills;
    private Set<SubVisitDto> subVisits;
}
