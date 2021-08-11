package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.SchemeType;
import com.cleviro.ErpManagerApp.model.masters.enums.SchemeTypes;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SchemeTypeRepository extends CrudRepository<SchemeType,Short> {
    Optional<SchemeType> findByName(SchemeTypes name);
}
