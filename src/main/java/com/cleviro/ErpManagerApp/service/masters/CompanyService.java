package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;

import java.util.Collection;

public interface CompanyService {

    /**
     * Get a company by id
     * @param id
     */
    CompanyDto findCompanyById(int id);

    /**
     * Find all the companies
     * @param
     */
    Collection<CompanyDto> findAllCompanies();

    /**
     *
     * Create a new company
     * @param companyDto
     */
    CompanyDto addCompany(CompanyDto companyDto);

    /**
     * update company details
     * @param companyDto
     */
    CompanyDto updateCompany(CompanyDto companyDto);

    /**
     * delete a company from db
     * @param id
     */
    void removeCompany(int id);
}
