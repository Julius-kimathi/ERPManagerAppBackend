package com.cleviro.ErpManagerApp.dto.mapper.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.CompanyDto;
import com.cleviro.ErpManagerApp.model.masters.Company;

public class CompanyMapper {
   public static CompanyDto toCompanyDto(Company company){
       return new CompanyDto()
               .setAbbreviation(company.getAbbreviation())
               .setCity(company.getCity())
               .setCountry(company.getCountry())
   }
}
