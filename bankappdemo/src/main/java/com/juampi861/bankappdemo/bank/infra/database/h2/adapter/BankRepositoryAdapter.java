package com.juampi861.bankappdemo.bank.infra.database.h2.adapter;

import com.juampi861.bankappdemo.bank.domain.model.Bank;
import com.juampi861.bankappdemo.bank.domain.port.BankRespositoryPort;
import com.juampi861.bankappdemo.bank.infra.database.h2.entity.BankEntity;
import com.juampi861.bankappdemo.bank.infra.database.h2.mapper.BankMapper;
import com.juampi861.bankappdemo.bank.infra.database.h2.repository.BankJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@RequiredArgsConstructor
@Component
public class BankRepositoryAdapter implements BankRespositoryPort {

    private final BankJpaRepository bankJpaRepository;


    @Override
    public List<Bank> getAllBanks() {
        return bankJpaRepository.findAll()
                .stream()
                .map(BankMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Bank> getBankById(String id) {
        return bankJpaRepository.findById(id)
                .map(BankMapper::toDomain);
    }

    @Override
    public void saveBank(Bank bank) {
        final BankEntity entity = BankMapper.toEntity(bank);
        bankJpaRepository.save(entity);
    }

    @Override
    public void removeBankById(String id) {
        bankJpaRepository.deleteById(id);
    }

    @Override
    public Boolean existBankById(String id) {
        return bankJpaRepository.existsById(id);
    }
}
