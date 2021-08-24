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
public class AddLocationRequest {
    private Short id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;
    private String abbreviation;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String postalAddress;
    private String state;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String city;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String phone;
    private String phone1;
    private String email;
    private String email1;
    private String status;

    private int companyId;
    private int zoneId;
}
