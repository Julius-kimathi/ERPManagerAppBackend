package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.CopayCategory;
import com.cleviro.ErpManagerApp.model.masters.enums.CopayCategories;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CopayCategoryRepository extends CrudRepository<CopayCategory,Short> {
    Optional<CopayCategory> findByName(CopayCategories name);
}
