package com.cleviro.ErpManagerApp.controller.v1.request.patients;

import com.cleviro.ErpManagerApp.model.patients.enums.DepartmentStatuses;
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
public class AddConsultationRequest {
    private Long id;
    @Enumerated(EnumType.STRING)
    private DepartmentStatuses status;
    private Long visitId;
}
