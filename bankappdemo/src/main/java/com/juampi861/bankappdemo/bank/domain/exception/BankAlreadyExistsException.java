package com.juampi861.bankappdemo.bank.domain.exception;

public class BankAlreadyExistsException extends RuntimeException {
    public BankAlreadyExistsException(final String message) {
        super(message);
    }
}
