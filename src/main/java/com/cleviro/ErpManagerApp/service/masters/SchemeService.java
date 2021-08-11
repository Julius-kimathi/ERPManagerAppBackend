package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.SchemeDto;

import java.util.Collection;

public interface SchemeService {
    /**
     * Get a scheme by id
     * @param id
     */
    SchemeDto findCompanyById(int id);

    /**
     * Find all the schemes
     * @param
     */
    Collection<SchemeDto> findAllschemes();

    /**
     *
     * Create a new scheme
     * @param schemeDto
     */
    SchemeDto addScheme(SchemeDto schemeDto);

    /**
     * update scheme details
     * @param schemeDto
     */
    SchemeDto updateScheme(SchemeDto schemeDto);

    /**
     * delete a scheme from db
     * @param id
     */
    void removeScheme(int id);
}
