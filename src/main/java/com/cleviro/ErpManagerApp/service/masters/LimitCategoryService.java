package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.LimitCategoryDto;

import java.util.Collection;

public interface LimitCategoryService {
    /**
     * Get a limitCategory by id
     * @param id
     */
    LimitCategoryDto findLimitCategoryById(Short id);

    /**
     * Find all the limitCategorys
     * @param
     */
    Collection<LimitCategoryDto> findAllLimitCategories();

    /**
     *
     * Create a new limitCategory
     * @param limitCategoryDto
     */
    LimitCategoryDto addLimitCategory(LimitCategoryDto limitCategoryDto);

    /**
     * update limitCategory details
     * @param limitCategoryDto
     */
    LimitCategoryDto updateLimitCategory(LimitCategoryDto limitCategoryDto);

    /**
     * delete a limitCategory from db
     * @param id
     */
    void removeLimitCategory(Short id);
}
