package com.cleviro.ErpManagerApp.service.people;

import com.cleviro.ErpManagerApp.dto.model.people.SupplierDto;

import java.util.Collection;

public interface SupplierService {
    /**
     * Get a supplier by id
     * @param id
     */
    SupplierDto findSupplierById(Long id);

    /**
     * Find all the suppliers
     * @param
     */
    Collection<SupplierDto> findAllSuppliers();

    /**
     *
     * Create a new supplier
     * @param supplierDto
     */
    SupplierDto addSupplier(SupplierDto supplierDto);

    /**
     * update supplier details
     * @param supplierDto
     */
    SupplierDto updateSupplier(SupplierDto supplierDto);

    /**
     * delete a supplier from db
     * @param id
     */
    void removeSupplier(Long id);
}
