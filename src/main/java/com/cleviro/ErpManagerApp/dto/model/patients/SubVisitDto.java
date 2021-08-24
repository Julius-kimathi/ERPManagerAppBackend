package com.cleviro.ErpManagerApp.dto.model.patients;

import com.cleviro.ErpManagerApp.dto.model.billing.DepartmentBillDto;
import com.cleviro.ErpManagerApp.dto.model.masters.LocationDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

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
public class SubVisitDto {
    private Long id;
    private String subVisitCode;
    private LocalDateTime subVisitDate;
    private BigDecimal availableLimit;
    private Boolean overallPaymentStatus;
    private BigDecimal copay;
    private String invoiceNo;
    private BigDecimal invoiceAmount;

    private VisitDto visit;
    private LocationDto location;
    private Set<DepartmentBillDto> departmentBills;
}
