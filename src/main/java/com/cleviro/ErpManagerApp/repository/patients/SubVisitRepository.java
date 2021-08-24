package com.cleviro.ErpManagerApp.repository.patients;

import com.cleviro.ErpManagerApp.model.patients.SubVisit;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SubVisitRepository extends CrudRepository<SubVisit,Long> {
    Optional<SubVisit> findBySubVisitCode(String subVisitCode);
}
