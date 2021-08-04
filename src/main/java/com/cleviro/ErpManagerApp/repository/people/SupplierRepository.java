package com.cleviro.ErpManagerApp.repository.people;

import com.cleviro.ErpManagerApp.model.people.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SupplierRepository extends CrudRepository<Supplier, Long> {
    Optional<Supplier> findByEmail(String email);
}
