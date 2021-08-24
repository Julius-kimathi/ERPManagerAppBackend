package com.cleviro.ErpManagerApp.repository.billing;

import com.cleviro.ErpManagerApp.dto.model.patients.VisitDto;
import com.cleviro.ErpManagerApp.model.billing.DepartmentBill;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentBillRepository extends CrudRepository<DepartmentBill,Long> {
    Optional<DepartmentBill> findByVisit(VisitDto visit);
}
