package com.cleviro.ErpManagerApp.controller.v1.request.patients;

import com.cleviro.ErpManagerApp.model.masters.enums.BillTypes;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddVisitRequest {
    private Long id;
    @Enumerated(EnumType.STRING)
    private BillTypes billType;

    private Long principalId;
    private Long dependentId;
    private Integer payerAccountId;
    private Integer planId;
    private Integer consultationRateId;
    private Integer locationId;
    private Integer companyId;

}
