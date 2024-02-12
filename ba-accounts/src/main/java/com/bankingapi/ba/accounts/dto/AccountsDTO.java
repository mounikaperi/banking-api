package com.bankingapi.ba.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name="Accounts", description = "Schema to hold account information")
public class AccountsDTO {

    @NotEmpty(message="AccountNumber cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
    @Schema(description = "AccountNumber of EazyBank account", example="3454433243")
    private Long accountNumber;

    @NotEmpty(message="AccountType cannot be null or empty")
    @Schema(description = "AccountType of EazyBank account", example="Savings")
    private String accountType;

    @NotEmpty(message="BranchAddress cannot be null or empty")
    @Schema(description = "EazyBank branch address", example = "123 NewYork")
    private String branchAddress;

}
