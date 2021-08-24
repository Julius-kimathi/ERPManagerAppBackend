package com.cleviro.ErpManagerApp.controller.v1.request.billing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddDepartmentBillDetailRequest {
    private Long id;
    private Integer quantity;
    private BigDecimal unitPrice;
}
