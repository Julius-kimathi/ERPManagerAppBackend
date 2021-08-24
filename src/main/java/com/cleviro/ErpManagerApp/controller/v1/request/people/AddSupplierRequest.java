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
public class AddSupplierRequest {
    private Long id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String email;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String phone;
    private String phone1;
    private String kraPin;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String postalAddress;
    private String state;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String city;
    private String status;
    private String website;

    private int countryId;
    @Min(value = 0, message = "{constraints.Min.message}")
    private Short supplierTypeId;
}
