package com.vaultsystem.customer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerBankDetailsDTO {
    private String bankId;
    private String bankName;
    private String bankCode;
    private String bankAccountId;

    public CustomerBankDetailsDTO() {}
    public CustomerBankDetailsDTO(String bankId, String bankName, String bankCode, String bankAccountId) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.bankAccountId = bankAccountId;
    }
}
