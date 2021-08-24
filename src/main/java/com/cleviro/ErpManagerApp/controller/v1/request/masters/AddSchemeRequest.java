package com.cleviro.ErpManagerApp.controller.v1.request.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddSchemeRequest {
    private int id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;
    private Boolean status;
    private Boolean isPreauthRequired;
    @Min(value = 0,message = "{constraints.Min.message}")
    private Short schemeTypeId;
    @Min(value = 0,message = "{constraints.Min.message}")
    private Integer payerId;
}
