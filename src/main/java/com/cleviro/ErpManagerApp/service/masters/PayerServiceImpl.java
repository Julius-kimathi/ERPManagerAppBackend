package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.mapper.masters.PayerMapper;
import com.cleviro.ErpManagerApp.dto.model.masters.PayerDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Payer;
import com.cleviro.ErpManagerApp.repository.masters.PayerRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import com.cleviro.ErpManagerApp.util.RandomStringUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class PayerServiceImpl implements PayerService{
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PayerRepository payerRepository;

    @Override
    public PayerDto findPayerById(int id) {
        Optional<Payer> payer = payerRepository.findById(id);
        if (payer.isPresent())
            return PayerMapper.toPayerDto(payer.get());
        else
            throw ExceptionUtil.exception(EntityType.PAYER, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }

    @Override
    public Collection<PayerDto> findAllPayers() {
        return StreamSupport.stream(payerRepository.findAll().spliterator(),false)
                .map(PayerMapper::toPayerDto)
                .collect(Collectors.toSet());
    }

    @Override
    public PayerDto addPayer(PayerDto payerDto) {
        Optional<Payer> payer = payerRepository.findByName(payerDto.getName());
        if (!payer.isPresent()){
            String code = RandomStringUtil.getAlphaNumericString(6,EntityType.PAYER);
            Payer payerModel = new Payer()
                    .setPayerCode(code)
                    .setName(payerDto.getName());
            return PayerMapper.toPayerDto(payerRepository.save(payerModel));
        }
        throw ExceptionUtil.exception(EntityType.PAYER, ExceptionType.DUPLICATE_ENTITY,payerDto.getName());
    }

    @Override
    public PayerDto updatePayer(PayerDto payerDto) {
        Optional<Payer> payer = payerRepository.findById(payerDto.getId());
        if (payer.isPresent()){
            Payer payerModel = payer.get()
                    .setName(payerDto.getName());
            return PayerMapper.toPayerDto(payerRepository.save(payerModel));
        }
        throw ExceptionUtil.exception(EntityType.PAYER, ExceptionType.ENTITY_NOT_FOUND,payerDto.toString());
    }

    @Override
    public void removePayer(int id) {
        Optional<Payer> payer = payerRepository.findById(id);
        if (payer.isPresent())
            payerRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.PAYER, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }
}
