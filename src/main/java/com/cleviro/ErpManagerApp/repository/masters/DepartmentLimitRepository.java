package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.DepartmentDto;
import com.cleviro.ErpManagerApp.dto.model.masters.PlanDto;
import com.cleviro.ErpManagerApp.model.masters.DepartmentLimit;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface DepartmentLimitRepository extends CrudRepository<DepartmentLimit,Short> {
    Optional<DepartmentLimit> findByDepartmentAndPlan(DepartmentDto department, PlanDto plan);
}
