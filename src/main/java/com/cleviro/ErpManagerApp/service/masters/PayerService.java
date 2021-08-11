package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PayerDto;

import java.util.Collection;

public interface PayerService {
    /**
     * Get a payer by id
     * @param id
     */
    PayerDto findPayerById(int id);

    /**
     * Find all the payers
     * @param
     */
    Collection<PayerDto> findAllPayers();

    /**
     *
     * Create a new payer
     * @param payerDto
     */
    PayerDto addPayer(PayerDto payerDto);

    /**
     * update payer details
     * @param payerDto
     */
    PayerDto updatePayer(PayerDto payerDto);

    /**
     * delete a payer from db
     * @param id
     */
    void removePayer(int id);
}
