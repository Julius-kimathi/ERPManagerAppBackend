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
public class SchemeDto {
    private int id;
    private String schemeCode;
    private String name;
    private Boolean status;
    private Boolean isPreauthRequired;

    private SchemeTypeDto schemeType;
    private PayerDto payer;
    private Set<PayerAccountDto> payerAccounts;
    private Set<ConsultationRateDto> consultationRates;
}
