package com.cleviro.ErpManagerApp.repository.people;

import com.cleviro.ErpManagerApp.model.people.CustomerType;
import org.springframework.data.repository.CrudRepository;

public interface CustomerTypeRepository extends CrudRepository<CustomerType, Short> {
    CustomerType findByName(String name);
}
