package com.vaultsystem.transactions.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Schema(name = "Customer", description = "Schema to validate customer information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerBankDetailsDTO {
    private String bankId;
    private String bankName;
    private String bankCode;
    private String bankAccountId;
    private String message;

    public CustomerBankDetailsDTO() {}
    public CustomerBankDetailsDTO(String bankId, String bankName, String bankCode, String bankAccountId, String message) {
        this.bankId = bankId;
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.bankAccountId = bankAccountId;
        this.message = message;
    }
}
