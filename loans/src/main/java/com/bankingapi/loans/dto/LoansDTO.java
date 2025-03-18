package com.bankingapi.loans.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
@Schema(name = "Loans", description = "Schema to hold loan information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoansDTO {

    @NotEmpty(message = "MobileNumber cannot be null or empty")
    @Pattern(regexp="(^$|[0-9]{10})", message = "MobileNumber must be 10 digits")
    @Schema(description = "MobileNumber of customer", example="9876543210")
    private String mobileNumber;

//    @NotEmpty(message = "LoanNumber cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{13})", message = "LoanNumber must be 13 digits")
    @Schema(description = "Loan Number of the customer", example = "123456789012")
    private String loanNumber;

    @NotEmpty(message = "LoanType cannot be null or empty")
    @Schema(description = "Type of the loan", example = "Home loan")
    private String loanType;

    @Positive(message = "Total loan amount should be greater than zero")
    @Schema(description = "Total loan amount", example = "100000")
    private Integer totalLoan;

    @PositiveOrZero(message = "Total loan amount paid should be equal or greater than zero")
    @Schema(description = "Total loan amount paid", example = "1000")
    private Integer amountPaid;

    @PositiveOrZero(message = "Total outstanding amount should be equal or greater than zero")
    @Schema(description = "Total outstanding amount against a loan", example = "99000")
    private Integer outstandingAmount;

    @Schema(description = "Message to be displayed if customer has no loan", example = "Customer has no loans")
    private String message;
}
