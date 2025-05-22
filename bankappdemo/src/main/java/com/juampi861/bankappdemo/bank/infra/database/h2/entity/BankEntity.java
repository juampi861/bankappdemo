package com.juampi861.bankappdemo.bank.infra.database.h2.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "banks")
public class BankEntity {
    @Id
    private String id;
    private String name;
    private String country;
    private String phoneNumber;
}
