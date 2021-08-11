package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.PlanCategory;
import com.cleviro.ErpManagerApp.model.masters.enums.PlanCategories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PlanCategoryRepository extends CrudRepository<PlanCategory,Short> {
    Optional<PlanCategory> findByName(PlanCategories name);
}
