package com.cleviro.ErpManagerApp.dto.model.patients;

import com.cleviro.ErpManagerApp.model.patients.enums.DepartmentStatuses;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TriageDto {
    private Long id;
    @Enumerated(EnumType.STRING)
    private DepartmentStatuses status;

    private VisitDto visit;
}
