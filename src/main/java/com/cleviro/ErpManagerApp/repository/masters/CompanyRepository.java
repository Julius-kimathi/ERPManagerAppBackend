package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.Company;
import org.springframework.data.repository.CrudRepository;

public interface CompanyRepository extends CrudRepository<Company, Integer>
{
    Company findByName(String name);
    Company findByEmail(String email);
}
