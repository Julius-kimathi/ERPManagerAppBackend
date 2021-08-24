package com.cleviro.ErpManagerApp.controller.v1.request.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddDepartmentLimitRequest {
    private Short id;
    @Min(value = 0, message = "{constraints.Min.message}")
    private Short departmentId;
    private BigDecimal overallLimit;
    private BigDecimal visitLimit;
    private BigDecimal copay;
}
