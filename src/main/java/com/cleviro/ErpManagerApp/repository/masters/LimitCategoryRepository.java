package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.LimitCategory;
import com.cleviro.ErpManagerApp.model.masters.enums.LimitCategories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LimitCategoryRepository extends CrudRepository<LimitCategory,Short> {
    Optional<LimitCategory> findByName(LimitCategories name);
}
