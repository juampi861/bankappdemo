package com.juampi861.bankappdemo.bank.domain.model;

import lombok.*;

@Data
@Builder
public class Bank {
    private String id;
    private String name;
    private String country;
    private String phoneNumber;
}
