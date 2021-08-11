package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PayerAccountDto;
import com.cleviro.ErpManagerApp.dto.model.masters.PayerDto;
import com.cleviro.ErpManagerApp.dto.model.masters.PlanDto;
import com.cleviro.ErpManagerApp.dto.model.masters.SchemeDto;
import com.cleviro.ErpManagerApp.model.masters.PayerAccount;
import org.modelmapper.ModelMapper;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class PayerAccountMapper {
    public static PayerAccountDto toPayerAccountDto(PayerAccount payerAccount){
        return new PayerAccountDto()
                .setId(payerAccount.getId())
                .setPayerAccountCode(payerAccount.getPayerAccountCode())
                .setName(payerAccount.getName())
                .setEmail(payerAccount.getEmail())
                .setPhone(payerAccount.getPhone())
                .setAddress(payerAccount.getAddress())
                .setStatus(payerAccount.getStatus())
                .setScheme(new ModelMapper().map(payerAccount.getScheme(), SchemeDto.class))
                .setPayer(new ModelMapper().map(payerAccount.getPayer(), PayerDto.class))
                .setPlans(StreamSupport.stream(payerAccount.getPlans().spliterator(),false).map(plan -> new ModelMapper().map(plan, PlanDto.class)).collect(Collectors.toSet()));
    }
}
