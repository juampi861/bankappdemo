package com.juampi861.bankappdemo.bank.domain.port;

import com.juampi861.bankappdemo.bank.domain.model.Bank;

import java.util.*;

/**
 * The Bank Repository Port
 */
public interface BankRespositoryPort {

    /**
     * Returns All the Banks
     *
     * @return List of Banks
     */
    List<Bank> getAllBanks();

    /**
     * Gets Bank with the given id
     *
     * @param id The given ID.
     * @return The Bank found
     */
    Optional<Bank> getBankById(String id);

    /**
     * Saves the given Bank
     *
     * @param bank The given Bank
     */
    void saveBank(Bank bank);

    /**
     * Removes the Bank existing with the given id
     *
     * @param id The given id
     */
    void removeBankById(String id);

    /**
     * Checks if a Bank with given ID exists
     *
     * @param id The Given ID
     * @return true if exists. False, in other case
     */
    Boolean existBankById(String id);
}
