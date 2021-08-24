package com.cleviro.ErpManagerApp.controller.v1.request.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddConsultationRateRequest {
    private int id;
  //  @NotEmpty(message = "{constraints.NotEmpty.message}")
    private BigDecimal fees;
    private Short consultationTypeId;
    private Long doctorId;
    @NotNull(message = "{constraints.NotNull.message}")
    private Integer locationId;
    @NotNull(message = "{constraints.NotNull.message}")
    private Integer schemeId;
    @NotNull(message = "{constraints.NotNull.message}")
    private Short departmentId;
}
