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
public class AddPayerAccountRequest {
    private int id;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private String name;
    private String email;
    private String phone;
    private String address;
    private Boolean status;
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Integer schemeId; //eg AAR FFS,AAR CAPITATION,BRITAM FFS,BRITAM CAPITATION ETC
    @NotEmpty(message = "{constraints.NotEmpty.message}")
    private Integer payerId;
}
