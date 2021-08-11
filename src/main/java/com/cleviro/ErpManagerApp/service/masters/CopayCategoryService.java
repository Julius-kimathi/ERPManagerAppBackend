package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.CopayCategoryDto;

import java.util.Collection;

public interface CopayCategoryService {
    /**
     * Get a copayCategory by id
     * @param id
     */
    CopayCategoryDto findCopayCategoryById(Short id);

    /**
     * Find all the copayCategorys
     * @param
     */
    Collection<CopayCategoryDto> findAllCopayCategories();

    /**
     *
     * Create a new copayCategory
     * @param copayCategoryDto
     */
    CopayCategoryDto addCopayCategory(CopayCategoryDto copayCategoryDto);

    /**
     * update copayCategory details
     * @param copayCategoryDto
     */
    CopayCategoryDto updateCopayCategory(CopayCategoryDto copayCategoryDto);

    /**
     * delete a copayCategory from db
     * @param id
     */
    void removeCopayCategory(Short id);
}
