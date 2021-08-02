package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.StoreDto;

import java.util.Collection;

public interface StoreService {
    /**
     * Get a store by id
     * @param id
     */
    StoreDto findStoreById(int id);

    /**
     * Find all the stores
     * @param
     */
    Collection<StoreDto> findAllStores();

    /**
     *
     * Create a new store
     * @param storeDto
     */
    StoreDto addStore(StoreDto storeDto);

    /**
     * update store details
     * @param storeDto
     */
    StoreDto updateStore(StoreDto storeDto);

    /**
     * delete a store from db
     * @param id
     */
    void removeStore(int id);
}
