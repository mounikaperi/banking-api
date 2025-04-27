package com.bankingapi.accounts.dto;

import lombok.Data;

@Data
public class BankAccountDetailsDTO {
    private String accountId;
    private String branchId;
    private String accountType;
    private String currencyCode;
    private String openDate;
    private String closeDate;
    private String accountStatus;
    private double accountBalance;
    // recent transactions
}
