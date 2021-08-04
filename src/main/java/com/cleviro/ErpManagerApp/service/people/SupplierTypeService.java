package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.model.people.SupplierTypeDto;

import java.util.Collection;

public interface SupplierTypeService {
    /**
     * Get a supplierType by id
     * @param id
     */
    SupplierTypeDto findSupplierTypeById(Short id);

    /**
     * Find all the supplierTypes
     * @param
     */
    Collection<SupplierTypeDto> findAllSupplierTypes();

    /**
     *
     * Create a new supplierType
     * @param supplierTypeDto
     */
    SupplierTypeDto addSupplierType(SupplierTypeDto supplierTypeDto);

    /**
     * update supplierType details
     * @param supplierTypeDto
     */
    SupplierTypeDto updateSupplierType(SupplierTypeDto supplierTypeDto);

    /**
     * delete a supplierType from db
     * @param id
     */
    void removeSupplierType(Short id);
}
