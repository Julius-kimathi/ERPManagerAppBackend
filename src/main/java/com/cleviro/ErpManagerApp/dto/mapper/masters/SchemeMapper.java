package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PayerDto;
import com.cleviro.ErpManagerApp.dto.model.masters.SchemeDto;
import com.cleviro.ErpManagerApp.dto.model.masters.SchemeTypeDto;
import com.cleviro.ErpManagerApp.model.masters.Scheme;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class SchemeMapper {
    public static SchemeDto toSchemeDto(Scheme scheme){
        return new SchemeDto()
                .setId(scheme.getId())
                .setSchemeCode(scheme.getSchemeCode())
                .setName(scheme.getName())
                .setStatus(scheme.getStatus())
                .setIsPreauthRequired(scheme.getIsPreauthRequired())
                .setSchemeType(new ModelMapper().map(scheme.getSchemeType(), SchemeTypeDto.class))
                .setPayer(new ModelMapper().map(scheme.getPayer(), PayerDto.class))
                .setPayerAccounts(StreamSupport.stream(scheme.getPayerAccounts().spliterator(),false).map(PayerAccountMapper::toPayerAccountDto).collect(Collectors.toSet()))
                .setConsultationRates(StreamSupport.stream(scheme.getConsultationRates().spliterator(),false).map(ConsultationRateMapper::toConsultationRateDto).collect(Collectors.toSet()));

    }
}
