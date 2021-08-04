package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.Zone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ZoneRepository extends CrudRepository<Zone, Integer> {
    Zone findByName(String name);

    List<Zone> findAllByName(String name);
}
