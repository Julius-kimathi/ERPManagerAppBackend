package com.cleviro.ErpManagerApp.dto.model.masters;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayerAccountDto {
    private int id;
    private String payerAccountCode;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Boolean status;
    private SchemeDto scheme; //eg AAR FFS,AAR CAPITATION,BRITAM FFS,BRITAM CAPITATION ETC
    private PayerDto payer;
    private Set<PlanDto> plans;
}
