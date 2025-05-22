package com.juampi861.bankappdemo.bank.application;

import com.juampi861.bankappdemo.bank.domain.exception.*;
import com.juampi861.bankappdemo.bank.domain.model.Bank;
import com.juampi861.bankappdemo.bank.domain.port.BankRespositoryPort;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultBankService implements BankService {
    private static final String BANK_ALREADY_EXISTS_ERROR_MSG = "A Bank already exists with the given id";
    private static final String BANK_NOT_FOUND_MSG = "Could not found the Bank with the given id";
    private static final String BANK_NOT_FOUND_LOG_MSG = "Cannot found Bank with id {}";
    private static final String A_BANK_ALREADY_EXISTS_LOG_MSG = "A Bank Already exists with id {}";

    private BankRespositoryPort bankRespositoryPort;

    @Override
    @CacheEvict(value = "allBanks", allEntries = true)
    public void createBank(final Bank bank) {
        if (BooleanUtils.isTrue(bankRespositoryPort.existBankById(bank.getId()))) {
            log.error(A_BANK_ALREADY_EXISTS_LOG_MSG, bank.getId());
            throw new BankAlreadyExistsException(BANK_ALREADY_EXISTS_ERROR_MSG);
        }

        bankRespositoryPort.saveBank(bank);
    }

    @Override
    @CacheEvict(value = "allBanks", allEntries = true)
    public void deleteBankById(final String id) {
        if (BooleanUtils.isFalse(bankRespositoryPort.existBankById(id))) {
            log.error(BANK_NOT_FOUND_LOG_MSG, id);
            throw new BankNotFoundException(BANK_NOT_FOUND_MSG);
        }
        bankRespositoryPort.removeBankById(id);
    }

    @Override
    @CacheEvict(value = "allBanks", allEntries = true)
    public void updateBankDetails(final Bank bank) {
        if (BooleanUtils.isFalse(bankRespositoryPort.existBankById(bank.getId()))) {
            log.error(BANK_NOT_FOUND_LOG_MSG, bank.getId());
            throw new BankNotFoundException(BANK_NOT_FOUND_MSG);
        }
        bankRespositoryPort.saveBank(bank);
    }

    @Override
    public Optional<Bank> getBankById(final String id) {
        if (BooleanUtils.isFalse(bankRespositoryPort.existBankById(id))) {
            log.error(BANK_NOT_FOUND_LOG_MSG, id);

            throw new BankNotFoundException(BANK_NOT_FOUND_MSG);
        }
        return bankRespositoryPort.getBankById(id);
    }

    @Override
    @Cacheable("allBanks")
    public List<Bank> getAllBanks() {
        return bankRespositoryPort.getAllBanks();
    }
}
