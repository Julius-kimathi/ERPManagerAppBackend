package com.cleviro.ErpManagerApp.controller.v1.request.people;

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
public class AddDesignationRequest {
    private Short id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;
    private String description;

    @Min(value = 0, message = "{constraints.Min.message}")
    private int companyId;
}
