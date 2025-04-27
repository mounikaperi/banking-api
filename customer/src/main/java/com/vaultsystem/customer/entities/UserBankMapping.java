package com.vaultsystem.customer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_bank_mapping")
@Getter
@Setter
@RequiredArgsConstructor
public class UserBankMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mappingId;

    @Column(name = "customerId")
    private String customerId;

    @Column(name = "bankId")
    private String bankId;

    @Column(name = "bankAccountId")
    private String bankAccountId;

    @Column(name = "accountLinked")
    private boolean accountLinked;

    public UserBankMapping(String customerId, String bankId, String bankAccountId, boolean accountLinked) {
        this.customerId = customerId;
        this.bankId = bankId;
        this.bankAccountId = bankAccountId;
        this.accountLinked = accountLinked;
    }
}
