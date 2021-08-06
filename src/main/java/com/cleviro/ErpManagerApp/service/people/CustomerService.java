package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.model.people.CustomerDto;

import java.util.Collection;

public interface CustomerService {
    /**
     * Get a customer by id
     * @param id
     */
    CustomerDto findCustomerById(Long id);

    /**
     * Get a customer by id
     * @param idNo
     */
    CustomerDto findCustomerByIdNo(String idNo);
    /**
     * Get a customer by id
     * @param email
     */
    CustomerDto findCustomerByEmail(String email);

    /**
     * Find all the customers
     * @param
     */
    Collection<CustomerDto> findAllCustomers();

    /**
     *
     * Create a new customer
     * @param customerDto
     */
    CustomerDto addCustomer(CustomerDto customerDto);

    /**
     * update customer details
     * @param customerDto
     */
    CustomerDto updateCustomer(CustomerDto customerDto);

    /**
     * delete a customer from db
     * @param id
     */
    void removeCustomer(Long id);
}
