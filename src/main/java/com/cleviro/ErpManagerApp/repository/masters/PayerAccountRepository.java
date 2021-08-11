package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PayerDto;
import com.cleviro.ErpManagerApp.dto.model.masters.SchemeDto;
import com.cleviro.ErpManagerApp.model.masters.PayerAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PayerAccountRepository extends CrudRepository<PayerAccount,Integer> {
    Optional<PayerAccount> findByNameAndSchemeAndPayer(String name, SchemeDto scheme, PayerDto payer);
}
