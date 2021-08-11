package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.PayerAccountMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.PayerAccountDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Payer;
import com.cleviro.ErpManagerApp.model.masters.PayerAccount;
import com.cleviro.ErpManagerApp.model.masters.Scheme;
import com.cleviro.ErpManagerApp.repository.masters.PayerAccountRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import com.cleviro.ErpManagerApp.util.RandomStringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PayerAccountServiceImpl implements PayerAccountService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PayerAccountRepository payerAccountRepository;

    @Override
    public PayerAccountDto findPayerAccountById(int id) {
        Optional<PayerAccount> payerAccount = payerAccountRepository.findById(id);
        if (payerAccount.isPresent())
            return PayerAccountMapper.toPayerAccountDto(payerAccount.get());
        else
            throw ExceptionUtil.exception(EntityType.PAYERACCOUNT, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }

    @Override
    public Collection<PayerAccountDto> findAllPayerAccounts() {
        return StreamSupport.stream(payerAccountRepository.findAll().spliterator(),false)
                .map(PayerAccountMapper::toPayerAccountDto)
                .collect(Collectors.toSet());
    }


    @Override
    @Transactional
    public PayerAccountDto addPayerAccount(PayerAccountDto payerAccountDto) {
        Optional<PayerAccount> payerAccount = payerAccountRepository.findByNameAndSchemeAndPayer(payerAccountDto.getName(),payerAccountDto.getScheme(),payerAccountDto.getPayer());
        if (!payerAccount.isPresent()){
            String payerAccountCode = RandomStringUtil.getAlphaNumericString(6,EntityType.PAYERACCOUNT);
            PayerAccount payerAccountModel = new PayerAccount()
                    .setPayerAccountCode(payerAccountCode)
                    .setName(payerAccountDto.getName())
                    .setEmail(payerAccountDto.getEmail())
                    .setPhone(payerAccountDto.getPhone())
                    .setAddress(payerAccountDto.getAddress())
                    .setStatus(payerAccountDto.getStatus())
                    .setScheme(modelMapper.map(payerAccountDto.getScheme(), Scheme.class))
                    .setPayer(modelMapper.map(payerAccountDto.getPayer(), Payer.class));
            return PayerAccountMapper.toPayerAccountDto(payerAccountRepository.save(payerAccountModel));

        }
        throw ExceptionUtil.exception(EntityType.PAYERACCOUNT, ExceptionType.DUPLICATE_ENTITY,payerAccountDto.toString());
    }

    @Override
    public PayerAccountDto updatePayerAccount(PayerAccountDto payerAccountDto) {
        Optional<PayerAccount> payerAccount = payerAccountRepository.findById(payerAccountDto.getId());
        if (!payerAccount.isPresent()){
            PayerAccount payerAccountModel = payerAccount.get()
                   // .setPayerAccountCode(payerAccountCode)
                    .setName(payerAccountDto.getName())
                    .setEmail(payerAccountDto.getEmail())
                    .setPhone(payerAccountDto.getPhone())
                    .setAddress(payerAccountDto.getAddress())
                    .setStatus(payerAccountDto.getStatus())
                    .setScheme(modelMapper.map(payerAccountDto.getScheme(), Scheme.class))
                    .setPayer(modelMapper.map(payerAccountDto.getPayer(), Payer.class));
            return PayerAccountMapper.toPayerAccountDto(payerAccountRepository.save(payerAccountModel));

        }
        throw ExceptionUtil.exception(EntityType.PAYERACCOUNT, ExceptionType.ENTITY_NOT_FOUND,payerAccountDto.toString());
    }

    @Override
    public void removePayerAccount(int id) {
        Optional<PayerAccount> payerAccount = payerAccountRepository.findById(id);
        if (payerAccount.isPresent())
            payerAccountRepository.deleteById(id);
        else  throw ExceptionUtil.exception(EntityType.PAYERACCOUNT, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }
}
