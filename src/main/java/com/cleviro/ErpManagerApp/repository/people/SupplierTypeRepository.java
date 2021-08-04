package com.cleviro.ErpManagerApp.repository.people;

import com.cleviro.ErpManagerApp.model.people.SupplierType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SupplierTypeRepository extends CrudRepository<SupplierType, Short> {
    Optional<SupplierType> findByName(String name);
}
