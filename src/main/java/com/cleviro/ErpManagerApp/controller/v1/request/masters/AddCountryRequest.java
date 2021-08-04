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
public class AddCountryRequest {
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Short mobileCode;
    private String abbreviation;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String currency;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Byte currencyCode;
    private Short currencySymbol;
}
