package com.juampi861.bankappdemo.bank.infra.database.h2.mapper;

import com.juampi861.bankappdemo.bank.domain.model.Bank;
import com.juampi861.bankappdemo.bank.infra.database.h2.entity.BankEntity;

public class BankMapper {
    private BankMapper() {

    }

    public static BankEntity toEntity(Bank bank) {
        BankEntity entity = new BankEntity();
        entity.setId(bank.getId());
        entity.setName(bank.getName());
        entity.setCountry(bank.getCountry());
        entity.setPhoneNumber(bank.getPhoneNumber());
        return entity;
    }

    public static Bank toDomain(BankEntity entity) {
        return Bank.builder()
                .id(entity.getId())
                .name(entity.getName())
                .country(entity.getCountry())
                .phoneNumber(entity.getPhoneNumber())
                .build();
    }
}