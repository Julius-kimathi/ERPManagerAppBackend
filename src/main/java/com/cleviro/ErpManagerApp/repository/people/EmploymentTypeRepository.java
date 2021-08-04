package com.cleviro.ErpManagerApp.repository.people;

import com.cleviro.ErpManagerApp.model.people.EmploymentType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmploymentTypeRepository extends CrudRepository<EmploymentType, Short> {
    Optional<EmploymentType> findByName(String name);
}
