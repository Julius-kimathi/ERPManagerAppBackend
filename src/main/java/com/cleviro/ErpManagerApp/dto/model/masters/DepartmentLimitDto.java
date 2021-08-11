package com.cleviro.ErpManagerApp.dto.model.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepartmentLimitDto {
    private Short id;
    private DepartmentDto department;
    private PlanDto plan;
    private BigDecimal overallLimit;
    private BigDecimal visitLimit;
    private BigDecimal copay;
}
