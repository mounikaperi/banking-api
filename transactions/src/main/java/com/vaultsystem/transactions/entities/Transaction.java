package com.vaultsystem.transactions.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Table(name="transactions")
public class Transaction {
    private String transactionId;
    private String accountId;
    private LocalDateTime transactionDate;
    private String transactionCurrencyCode;
    private char transactionType;
    private String transactionParticulars;
    private double transactionAmount;
    private String channel;
    private String transactionStatus;


}
