package com.cleviro.ErpManagerApp.controller.v1.request.billing;

import com.cleviro.ErpManagerApp.model.patients.enums.PaymentStatuses;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddDepartmentBillRequest {
    private Long id;
    @Enumerated(EnumType.STRING)
    private PaymentStatuses paymentStatus;
    private Short departmentId;
    private Long visitId;
    private Long subVisitId;
    private Set<AddDepartmentBillDetailRequest> departmentBillDetails;
}
