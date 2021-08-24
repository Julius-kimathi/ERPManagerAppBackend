package com.cleviro.ErpManagerApp.repository.patients;

import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.model.patients.Triage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TriageRepository extends CrudRepository<Triage,Long> {
    Optional<Triage> findByVisit(VisitDto visitDto);
}
