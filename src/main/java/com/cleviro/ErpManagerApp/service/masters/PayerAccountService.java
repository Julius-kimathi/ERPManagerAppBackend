package com.cleviro.ErpManagerApp.service.masters;

import com.cleviro.ErpManagerApp.dto.model.masters.PayerAccountDto;

import java.util.Collection;

public interface PayerAccountService {
    /**
     * Get a payerAccount by id
     * @param id
     */
    PayerAccountDto findPayerAccountById(int id);

    /**
     * Find all the payerAccounts
     * @param
     */
    Collection<PayerAccountDto> findAllPayerAccounts();

    /**
     *
     * Create a new payerAccount
     * @param payerAccountDto
     */
    PayerAccountDto addPayerAccount(PayerAccountDto payerAccountDto);

    /**
     * update payerAccount details
     * @param payerAccountDto
     */
    PayerAccountDto updatePayerAccount(PayerAccountDto payerAccountDto);

    /**
     * delete a payerAccount from db
     * @param id
     */
    void removePayerAccount(int id);
}
