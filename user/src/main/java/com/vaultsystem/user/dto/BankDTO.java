package com.vaultsystem.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.vaultsystem.user.model.UserBankMapping;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(name = "Bank", description = "Schema to validate user information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BankDTO {

    @Schema(description = "mappingId to map the user and the bank", example="mappingId")
    private long mappingId;

    @Schema(description = "customerId of the registered account in the bank", example="speri")
    private String customerId;

    @Schema(description = "bankId of the bank", example="speri")
    private String bankId;

    @NotEmpty(message = "BankAccountId of the user cannot be null or empty")
    @Schema(description = "BankAccountId of the registered user", example="")
    private String bankAccountId;

    @Schema(description = "BankAccountId of the registered user", example="")
    private String accountLinked;
}