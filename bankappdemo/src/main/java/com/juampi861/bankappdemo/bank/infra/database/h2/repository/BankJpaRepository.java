package com.juampi861.bankappdemo.bank.infra.database.h2.repository;

import com.juampi861.bankappdemo.bank.infra.database.h2.entity.BankEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankJpaRepository extends JpaRepository<BankEntity, String> {

}