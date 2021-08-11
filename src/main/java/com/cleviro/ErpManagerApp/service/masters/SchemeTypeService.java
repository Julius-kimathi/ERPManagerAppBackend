package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.SchemeTypeDto;

import java.util.Collection;

public interface SchemeTypeService {
    /**
     * Get a schemeType by id
     * @param id
     */
    SchemeTypeDto findSchemeTypeById(Short id);

    /**
     * Find all the schemeTypes
     * @param
     */
    Collection<SchemeTypeDto> findAllSchemeTypes();

    /**
     *
     * Create a new schemeType
     * @param schemeTypeDto
     */
    SchemeTypeDto addSchemeType(SchemeTypeDto schemeTypeDto);

    /**
     * update schemeType details
     * @param schemeTypeDto
     */
    SchemeTypeDto updateSchemeType(SchemeTypeDto schemeTypeDto);

    /**
     * delete a schemeType from db
     * @param id
     */
    void removeSchemeType(Short id);
}
