package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.Payer;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PayerRepository extends CrudRepository<Payer, Integer> {
    Optional<Payer> findByName(String name);
}
