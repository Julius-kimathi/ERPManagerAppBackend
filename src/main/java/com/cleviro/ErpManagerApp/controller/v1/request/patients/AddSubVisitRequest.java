package com.cleviro.ErpManagerApp.controller.v1.request.patients;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddSubVisitRequest {
    private Long id;
    @Min(value = 0,message = "{constraints.Min.message}")
    private Integer locationId;
    @Min(value = 0,message = "{constraints.Min.message}")
    private Long visitId;
}
