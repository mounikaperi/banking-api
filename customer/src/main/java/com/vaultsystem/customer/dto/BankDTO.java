package com.vaultsystem.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Bank", description = "Schema to validate bank information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankDTO {

    @Schema(description = "Id of the bank", example="bankId")
    @NotEmpty(message = "BankId of the Bank cannot be null or empty")
    @Size(max = 10)
    private String bankId;

    @Schema(description = "name of the bank", example="AXIS")
    @NotEmpty(message = "bankName of the bank cannot be null or empty")
    @Size(max = 10)
    private String bankName;

    @Schema(description = "code of the bank", example="speri")
    @NotEmpty(message = "bankCode of the bank cannot be null or empty")
    @Size(max = 10)
    private String bankCode;
}