package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.CountryDto;

import java.util.Collection;

public interface CountryService {
    /**
     * Get a country by id
     * @param id
     */
    CountryDto findCountryById(int id);

    /**
     * Find all the countries
     * @param
     */
    Collection<CountryDto> findAllCountries();

    /**
     *
     * Create a new country
     * @param countryDto
     */
    CountryDto addCountry(CountryDto countryDto);

    /**
     * update country details
     * @param companyDto
     */
    CountryDto updateCountry(CountryDto companyDto);

    /**
     * delete a country from db
     * @param id
     */
    void removeCountry(int id);
}
