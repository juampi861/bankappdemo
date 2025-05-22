package com.juampi861.bankappdemo.bank.infra.rest.mapper;

import com.juampi861.bankappdemo.bank.domain.model.Bank;
import com.juampi861.bankappdemo.bank.infra.rest.dto.*;

public class BankWebMapper {
    public static Bank toDomain(final BankRequestDTO dto) {
        return Bank.builder()
                .id(dto.id())
                .name(dto.name())
                .country(dto.country())
                .phoneNumber(dto.phoneNumber())
                .build();
    }

    public static BankResponseDTO toResponseDTO(Bank bank) {
        return new BankResponseDTO(
                bank.getId(),
                bank.getName(),
                bank.getCountry(),
                bank.getPhoneNumber()
        );
    }
}
