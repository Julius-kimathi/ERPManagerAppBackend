package com.cleviro.ErpManagerApp.controller.v1.request.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddDepartmentLimitRequest {
    private Short id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Short departmentId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Integer planId;
    private BigDecimal overallLimit;
    private BigDecimal visitLimit;
    private BigDecimal copay;
}
