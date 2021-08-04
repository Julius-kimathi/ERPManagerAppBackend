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
public class AddCompanyRequest {
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String postalAddress;
    private String state;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String city;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String phone;
    private String phone1;
    private String phone2;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String email;
    private String email1;
    private String email2;
    private String abbreviation;
    private String website;
    private String orderPrefix;


    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private int countryId;
}
