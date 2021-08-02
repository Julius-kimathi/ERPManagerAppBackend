package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Short> {
    Department findByName(String name);
}
