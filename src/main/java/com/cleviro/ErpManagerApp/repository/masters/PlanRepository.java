package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PayerAccountDto;
import com.cleviro.ErpManagerApp.model.masters.Plan;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlanRepository extends CrudRepository<Plan,Integer> {
    Optional<Plan> findByNameAndPayerAccount(String name, PayerAccountDto payerAccount);
}
