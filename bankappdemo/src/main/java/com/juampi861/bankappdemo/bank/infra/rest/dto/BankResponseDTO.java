package com.juampi861.bankappdemo.bank.infra.rest.dto;

public record BankResponseDTO(
        String id,
        String name,
        String country,
        String phoneNumber
) {
}
