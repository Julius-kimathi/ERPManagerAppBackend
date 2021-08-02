package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Integer> {
    Location findByName(String name);
}
