package com.cleviro.ErpManagerApp.service.people;


import com.cleviro.ErpManagerApp.dto.model.people.CustomerTypeDto;

import java.util.Collection;

public interface CustomerTypeService {
    /**
     * Get a customerType by id
     * @param id
     */
    CustomerTypeDto findCustomerTypeById(Short id);

    /**
     * Find all the customer types
     * @param
     */
    Collection<CustomerTypeDto> findAllCustomerTypes();

    /**
     *
     * Create a new customerType
     * @param customerTypeDto
     */
    CustomerTypeDto addCustomerType(CustomerTypeDto customerTypeDto);

    /**
     * update customerType details
     * @param customerTypeDto
     */
    CustomerTypeDto updateCustomerType(CustomerTypeDto customerTypeDto);

    /**
     * delete a customerType from db
     * @param id
     */
    void removeCustomerType(Short id);
}
