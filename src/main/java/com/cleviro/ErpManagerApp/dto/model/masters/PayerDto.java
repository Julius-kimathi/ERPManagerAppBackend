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
public class PayerDto {
    private int id;
    private String payerCode;
    private String name; //eg BRITAM,AAR,CIC,SANLAM,JUBILEE
    private Set<SchemeDto> schemes;
    private Set<PayerAccountDto> payerAccounts;
}
