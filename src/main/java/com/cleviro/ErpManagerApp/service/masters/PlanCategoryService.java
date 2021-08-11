package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PlanCategoryDto;

import java.util.Collection;

public interface PlanCategoryService {
    /**
     * Get a planCategory by id
     * @param id
     */
    PlanCategoryDto findPlanCategoryById(Short id);

    /**
     * Find all the planCategorys
     * @param
     */
    Collection<PlanCategoryDto> findAllPlanCategories();

    /**
     *
     * Create a new planCategory
     * @param planCategoryDto
     */
    PlanCategoryDto addPlanCategory(PlanCategoryDto planCategoryDto);

    /**
     * update planCategory details
     * @param planCategoryDto
     */
    PlanCategoryDto updatePlanCategory(PlanCategoryDto planCategoryDto);

    /**
     * delete a planCategory from db
     * @param id
     */
    void removePlanCategory(Short id);
}
