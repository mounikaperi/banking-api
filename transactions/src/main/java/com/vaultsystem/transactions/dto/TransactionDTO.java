package com.vaultsystem.transactions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;

@Data
@Document(indexName = "#{@environment.getProperty('spring.elasticsearch.index.transactions')}")
@Getter @Setter @AllArgsConstructor
public class TransactionDTO {
    private String transactionId;
    private String accountId;
    private LocalDateTime transactionDate;
    private String transactionCurrencyCode;
    private char transactionType;
    private String transactionParticulars;
    private double transactionAmount;
    private String channel;
    private String transactionStatus;
    private String bankName;
}
