package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.ConsultationType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConsultationTypeRepository extends CrudRepository<ConsultationType,Short> {
    Optional<ConsultationType> findByName(String name);
}
