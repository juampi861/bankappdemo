package com.juampi861.bankappdemo.bank.infra.rest.dto;

import jakarta.validation.constraints.*;

public record BankRequestDTO(
        @NotBlank(message = "ID is required")
        String id,

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Country is required")
        String country,

        @Pattern(regexp = "^[+]?\\d{7,15}$", message = "Phone number format is invalid")
        String phoneNumber
) {
}
