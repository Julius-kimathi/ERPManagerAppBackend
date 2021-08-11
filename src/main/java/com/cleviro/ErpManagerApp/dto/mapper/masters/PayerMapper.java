package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PayerAccountDto;
import com.cleviro.ErpManagerApp.dto.model.masters.PayerDto;
import com.cleviro.ErpManagerApp.dto.model.masters.SchemeDto;
import com.cleviro.ErpManagerApp.model.masters.Payer;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PayerMapper {
    public static PayerDto toPayerDto(Payer payer){
        return new PayerDto()
                .setId(payer.getId())
                .setPayerCode(payer.getPayerCode())
                .setName(payer.getName())
                .setSchemes(StreamSupport.stream(payer.getSchemes().spliterator(), false).map(scheme -> new ModelMapper().map(scheme, SchemeDto.class)).collect(Collectors.toSet()))
                .setPayerAccounts(StreamSupport.stream(payer.getPayerAccounts().spliterator(),false).map(payerAccount -> new ModelMapper().map(payerAccount, PayerAccountDto.class)).collect(Collectors.toSet()));
    }
}
