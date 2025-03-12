package com.bankingapi.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Loans", description = "Schema to hold Loan information")
@Data
public class LoansDTO {

    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @Schema(description = "Mobile Number of Customer", example = "9876543210")
    private String mobileNumber;

    @NotEmpty(message = "LoanNumber cannot be null or empty")
    @Pattern(regexp = "(^$[0-9]{12})", message = "LoanNumber must be 12 digits")
    @Schema(description = "LoanNumber of the customer", example = "567890432112")
    private String loanNumber;

    @NotEmpty(message = "LoanType cannot be null or empty")
    @Schema(description = "Type of the loan", example = "Home Loan")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(description = "Total Loan amount", example = "100000")
    private int totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(description = "Total outstanding amount against a loan", example = "1000")
    private int amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(description = "Total outstanding amount against a loan", example = "99000")
    private int outstandingAmount;
}
