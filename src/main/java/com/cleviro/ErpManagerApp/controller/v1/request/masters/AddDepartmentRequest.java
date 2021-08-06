package com.cleviro.ErpManagerApp.controller.v1.request.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddDepartmentRequest {
    private Short id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;
    private String description;

    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private int companyId;
}
