package com.vaultsystem.customer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "banks")
@Getter
@Setter
@RequiredArgsConstructor
public class Bank {

    private String bankId;
    private String bankName;
    private String bankCode;
}
