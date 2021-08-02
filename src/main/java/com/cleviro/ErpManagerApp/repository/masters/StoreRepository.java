package com.cleviro.ErpManagerApp.repository.masters;

import com.cleviro.ErpManagerApp.model.masters.Store;
import org.springframework.data.repository.CrudRepository;

public interface StoreRepository extends CrudRepository<Store, Integer> {
    Store findByName(String name);
}
