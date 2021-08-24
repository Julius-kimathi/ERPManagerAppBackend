package com.cleviro.ErpManagerApp.dto.model.billing;

import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;
import com.cleviro.ErpManagerApp.dto.model.patients.SubVisitDto;
import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.model.patients.enums.PaymentStatuses;
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
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentBillDto {
    private Long id;
    private BigDecimal billAmount;
    @Enumerated(EnumType.STRING)
    private PaymentStatuses paymentStatus;

    private DepartmentDto department;
    private VisitDto visit;
    private SubVisitDto subVisit;
    private Set<DepartmentBillDetailDto> departmentBillDetails;
}
