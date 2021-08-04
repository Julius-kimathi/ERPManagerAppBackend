package com.cleviro.ErpManagerApp.repository.people;

import com.cleviro.ErpManagerApp.model.people.Designation;
import org.springframework.data.repository.CrudRepository;

public interface DesignationRepository extends CrudRepository<Designation, Short> {
    Designation findByName(String name);
}
