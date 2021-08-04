package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.mapper.people.CustomerTypeMapper;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerTypeDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Company;
import com.cleviro.ErpManagerApp.model.people.CustomerType;
import com.cleviro.ErpManagerApp.repository.people.CustomerTypeRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CustomerTypeServiceImpl implements CustomerTypeService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public CustomerTypeDto findCustomerTypeById(Short id) {
        Optional<CustomerType> customerType = customerTypeRepository.findById(id);
        if (customerType.isPresent())
            return CustomerTypeMapper.toCustomerTypeDto(customerType.get());
            else
                throw ExceptionUtil.exception(EntityType.CUSTOMERTYPE, ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }

    @Override
    public Collection<CustomerTypeDto> findAllCustomerTypes() {
        return StreamSupport.stream(customerTypeRepository.findAll().spliterator(), false)
                .map(CustomerTypeMapper::toCustomerTypeDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CustomerTypeDto addCustomerType(CustomerTypeDto customerTypeDto) {
        Optional<CustomerType> customerType = Optional.ofNullable(customerTypeRepository.findByName(customerTypeDto.getName()));
        if (!customerType.isPresent()){
            CustomerType customerTypeModel = new CustomerType()
                    .setName(customerTypeDto.getName())
                    .setDescription(customerTypeDto.getDescription())
                    .setCompany(modelMapper.map(customerTypeDto.getCompany(), Company.class));
            return CustomerTypeMapper.toCustomerTypeDto(customerTypeRepository.save(customerTypeModel));
        }
        throw ExceptionUtil.exception(EntityType.CUSTOMERTYPE,ExceptionType.DUPLICATE_ENTITY, customerTypeDto.getName());
    }

    @Override
    public CustomerTypeDto updateCustomerType(CustomerTypeDto customerTypeDto) {
        Optional<CustomerType> customerType = customerTypeRepository.findById(customerTypeDto.getId());
        if (customerType.isPresent()){
            CustomerType customerTypeModel = customerType.get()
                    .setName(customerTypeDto.getName())
                    .setDescription(customerTypeDto.getDescription())
                    .setCompany(modelMapper.map(customerTypeDto.getCompany(), Company.class));
            return CustomerTypeMapper.toCustomerTypeDto(customerTypeRepository.save(customerTypeModel));
        }
        throw ExceptionUtil.exception(EntityType.CUSTOMERTYPE, ExceptionType.ENTITY_NOT_FOUND, customerTypeDto.getId().toString());
    }

    @Override
    public void removeCustomerType(Short id) {
        Optional<CustomerType> customerType = customerTypeRepository.findById(id);
        if (customerType.isPresent()) customerTypeRepository.deleteById(id);
        else throw ExceptionUtil.exception(EntityType.CUSTOMERTYPE,ExceptionType.ENTITY_NOT_FOUND,id.toString());
    }
}
