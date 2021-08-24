package com.cleviro.ErpManagerApp.repository.patients;

import com.cleviro.ErpManagerApp.model.patients.Visit;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VisitRepository extends CrudRepository<Visit,Long> {
    Optional<Visit> findByVisitCode(String visitCode);
}
