package com.juampi861.bankappdemo.bank.domain.exception;

public class BankNotFoundException extends RuntimeException {
    public BankNotFoundException(final String message) {
        super(message);
    }
}
