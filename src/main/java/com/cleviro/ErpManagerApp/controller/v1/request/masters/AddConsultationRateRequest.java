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
public class AddConsultationRateRequest {
    private int id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private BigDecimal fees;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Short consultationTypeId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Long doctorId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Integer locationId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Integer schemeId;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Short departmentId;
}
