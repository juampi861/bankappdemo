package com.juampi861.bankappdemo.bank.application;

import com.juampi861.bankappdemo.bank.domain.model.Bank;

import java.util.*;

/**
 * The Bank Service
 */
public interface BankService {
    /**
     * Creates a Bank in the system
     *
     * @param bank The given Bank to be created in the system
     */
    void createBank(Bank bank);

    /**
     * Deletes a Bank with the given id
     *
     * @param id The given Bank ID
     */
    void deleteBankById(String id);

    /**
     * Updates the details of a given Bank
     *
     * @param bank The given Bank
     */
    void updateBankDetails(Bank bank);

    /**
     * Gets a Bank with the given ID
     *
     * @param id The given ID
     * @return the Bank if exists. Optional.empty in other case
     */
    Optional<Bank> getBankById(String id);

    /**
     * Gets all the Banks existing in the system
     *
     * @return All Banks
     */
    List<Bank> getAllBanks();

}
