package com.vaultsystem.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Bank", description = "Schema to validate customer information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBankMappingDTO {

    @Schema(description = "mappingId to map the customer and the bank", example="mappingId")
    private long mappingId;

    @Schema(description = "customerId of the registered account in the bank", example="speri")
    private String customerId;

    @Schema(description = "bankId of the bank", example="speri")
    private String bankId;

    @Schema(description = "BankAccountId of the registered customer", example="")
    private String bankAccountId;

    @Schema(description = "BankAccountId of the registered customer", example="")
    private String accountLinked;
}