package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PayerDto;
import com.cleviro.ErpManagerApp.dto.model.masters.SchemeTypeDto;
import com.cleviro.ErpManagerApp.model.masters.Scheme;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SchemeRepository extends CrudRepository<Scheme,Integer> {
    Optional<Scheme> findByNameAndPayerAndSchemeType(String name, PayerDto payer, SchemeTypeDto schemeType);
}
