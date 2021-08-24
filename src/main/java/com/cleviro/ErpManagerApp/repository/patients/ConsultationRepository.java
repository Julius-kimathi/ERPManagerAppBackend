package com.cleviro.ErpManagerApp.repository.patients;

import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.model.patients.Consultation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ConsultationRepository extends CrudRepository<Consultation,Long> {
    Optional<Consultation> findByVisit(VisitDto visitDto);
}
