package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.mapper.people.CustomerMapper;
import com.cleviro.ErpManagerApp.dto.model.people.CustomerDto;
import com.cleviro.ErpManagerApp.exception.EntityType;
import com.cleviro.ErpManagerApp.exception.ExceptionType;
import com.cleviro.ErpManagerApp.model.masters.Country;
import com.cleviro.ErpManagerApp.model.people.Customer;
import com.cleviro.ErpManagerApp.model.people.CustomerType;
import com.cleviro.ErpManagerApp.repository.people.CustomerRepository;
import com.cleviro.ErpManagerApp.util.ExceptionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public CustomerDto findCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent())
            return CustomerMapper.toCustomerDto(customer.get());
        else
            throw ExceptionUtil.exception(EntityType.CUSTOMER, ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }

    @Override
    public CustomerDto findCustomerByIdNo(String idNo) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByIdNo(idNo));
        if (customer.isPresent())
            return CustomerMapper.toCustomerDto(customer.get());
        else
            throw ExceptionUtil.exception(EntityType.CUSTOMER, ExceptionType.ENTITY_NOT_FOUND,idNo);
    }

    @Override
    public CustomerDto findCustomerByEmail(String email) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByEmail(email));
        if (customer.isPresent())
            return CustomerMapper.toCustomerDto(customer.get());
        else
            throw ExceptionUtil.exception(EntityType.CUSTOMER, ExceptionType.ENTITY_NOT_FOUND,email);
    }


    @Override
    public Collection<CustomerDto> findAllCustomers() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(), false)
                .map(CustomerMapper::toCustomerDto)
                .collect(Collectors.toSet());
    }

    @Override
    public CustomerDto addCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = Optional.ofNullable(customerRepository.findByEmail(customerDto.getEmail()));
        if (!customer.isPresent()){
            Optional<Customer> customer1 = Optional.ofNullable(customerRepository.findByIdNo(customerDto.getIdNo()));
            if (!customer1.isPresent()){
               Customer customerModel = new Customer()
                       .setFirstName(customerDto.getFirstName())
                       .setMiddleName(customerDto.getMiddleName())
                       .setLastName(customerDto.getLastName())
                       .setDob(customerDto.getDob())
                       .setEmail(customerDto.getEmail())
                       .setPhone(customerDto.getPhone())
                       .setPhone1(customerDto.getPhone1())
                       .setIdNo(customerDto.getIdNo())
                       .setPostalAddress(customerDto.getPostalAddress())
                       .setCity(customerDto.getCity())
                       .setState(customerDto.getState())
                       .setStatus(customerDto.getStatus())
                       .setRegDate(customerDto.getRegDate())
                       .setGender(customerDto.getGender())
                       .setCountry(modelMapper.map(customerDto.getCountry(), Country.class))
                       .setCustomerType(modelMapper.map(customerDto.getCustomerType(), CustomerType.class));
               return CustomerMapper.toCustomerDto(customerRepository.save(customerModel));
            }
            throw ExceptionUtil.exception(EntityType.CUSTOMER, ExceptionType.DUPLICATE_ENTITY,customerDto.getIdNo());
        }
        throw ExceptionUtil.exception(EntityType.CUSTOMER, ExceptionType.DUPLICATE_ENTITY,customerDto.getEmail());
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = customerRepository.findById(customerDto.getId());
        if (customer.isPresent()){
            Customer customerModel = customer.get()
                    .setFirstName(customerDto.getFirstName())
                    .setMiddleName(customerDto.getMiddleName())
                    .setLastName(customerDto.getLastName())
                    .setDob(customerDto.getDob())
                    .setEmail(customerDto.getEmail())
                    .setPhone(customerDto.getPhone())
                    .setPhone1(customerDto.getPhone1())
                    .setIdNo(customerDto.getIdNo())
                    .setPostalAddress(customerDto.getPostalAddress())
                    .setCity(customerDto.getCity())
                    .setState(customerDto.getState())
                    .setStatus(customerDto.getStatus())
                    .setRegDate(customerDto.getRegDate())
                    .setGender(customerDto.getGender())
                    .setCountry(modelMapper.map(customerDto.getCountry(), Country.class))
                    .setCustomerType(modelMapper.map(customerDto.getCustomerType(), CustomerType.class));
            return CustomerMapper.toCustomerDto(customerRepository.save(customerModel));
        }
        throw ExceptionUtil.exception(EntityType.CUSTOMER, ExceptionType.ENTITY_NOT_FOUND,customerDto.getIdNo(), customerDto.getEmail());
    }

    @Override
    public void removeCustomer(Long id) {
        if (customerRepository.findById(id).isPresent())
            customerRepository.deleteById(id);
                    else
                        throw  ExceptionUtil.exception(EntityType.CUSTOMER,ExceptionType.ENTITY_NOT_FOUND,String.valueOf(id));
    }
}
