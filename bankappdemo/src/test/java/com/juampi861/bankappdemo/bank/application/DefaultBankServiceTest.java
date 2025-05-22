package com.juampi861.bankappdemo.bank.application;

import com.juampi861.bankappdemo.bank.domain.exception.BankAlreadyExistsException;
import com.juampi861.bankappdemo.bank.domain.exception.BankNotFoundException;
import com.juampi861.bankappdemo.bank.domain.model.Bank;
import com.juampi861.bankappdemo.bank.domain.port.BankRespositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DefaultBankServiceTest {

    @Mock
    private BankRespositoryPort bankRespositoryPort;

    @InjectMocks
    private DefaultBankService defaultBankService;

    private final String BANK_ID = "bank-123";
    private final Bank bank = Bank.builder().id(BANK_ID).build();

    @Test
    void createBank_shouldThrowWhenBankExists() {
        when(bankRespositoryPort.existBankById(BANK_ID)).thenReturn(true);

        final Exception exception = assertThrows(
                BankAlreadyExistsException.class,
                () -> defaultBankService.createBank(bank)
        );

        assertEquals("A Bank already exists with the given id", exception.getMessage());
        verify(bankRespositoryPort, never()).saveBank(bank);
    }

    @Test
    void createBank_shouldSaveWhenBankDoesNotExist() {
        when(bankRespositoryPort.existBankById(BANK_ID)).thenReturn(false);

        defaultBankService.createBank(bank);

        verify(bankRespositoryPort).saveBank(bank);
    }

    @Test
    void deleteBankById_shouldThrowWhenBankNotFound() {
        when(bankRespositoryPort.existBankById(BANK_ID)).thenReturn(false);

        final Exception exception = assertThrows(
                BankNotFoundException.class,
                () -> defaultBankService.deleteBankById(BANK_ID)
        );

        assertEquals("Could not found the Bank with the given id", exception.getMessage());
        verify(bankRespositoryPort, never()).removeBankById(BANK_ID);
    }

    @Test
    void deleteBankById_shouldRemoveWhenBankExists() {
        when(bankRespositoryPort.existBankById(BANK_ID)).thenReturn(true);

        defaultBankService.deleteBankById(BANK_ID);

        verify(bankRespositoryPort).removeBankById(BANK_ID);
    }

    @Test
    void updateBankDetails_shouldThrowWhenBankNotFound() {
        when(bankRespositoryPort.existBankById(BANK_ID)).thenReturn(false);

        final Exception exception = assertThrows(
                BankNotFoundException.class,
                () -> defaultBankService.updateBankDetails(bank)
        );

        assertEquals("Could not found the Bank with the given id", exception.getMessage());
        verify(bankRespositoryPort, never()).saveBank(bank);
    }

    @Test
    void updateBankDetails_shouldSaveWhenBankExists() {
        when(bankRespositoryPort.existBankById(BANK_ID)).thenReturn(true);

        defaultBankService.updateBankDetails(bank);

        verify(bankRespositoryPort).saveBank(bank);
    }

    @Test
    void getBankById_shouldThrowWhenBankNotFound() {
        when(bankRespositoryPort.existBankById(BANK_ID)).thenReturn(false);

        final Exception exception = assertThrows(
                BankNotFoundException.class,
                () -> defaultBankService.getBankById(BANK_ID)
        );

        assertEquals("Could not found the Bank with the given id", exception.getMessage());
        verify(bankRespositoryPort, never()).getBankById(BANK_ID);
    }

    @Test
    void getBankById_shouldReturnBankWhenExists() {
        when(bankRespositoryPort.existBankById(BANK_ID)).thenReturn(true);
        when(bankRespositoryPort.getBankById(BANK_ID)).thenReturn(Optional.of(bank));

        final Optional<Bank> result = defaultBankService.getBankById(BANK_ID);

        assertTrue(result.isPresent());
        assertEquals(bank, result.get());
    }

    @Test
    void getAllBanks_shouldReturnBankList() {
        final List<Bank> banks = List.of(bank);
        when(bankRespositoryPort.getAllBanks()).thenReturn(banks);

        final List<Bank> result = defaultBankService.getAllBanks();

        assertEquals(banks, result);
    }
}
