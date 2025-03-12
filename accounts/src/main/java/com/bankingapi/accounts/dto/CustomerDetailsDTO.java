package com.bankingapi.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "CustomerDetails", description = "Schema to hold Customer, Account, Cards, Loans information")
public class CustomerDetailsDTO {

    @Schema(description = "Name of the customer", example = "Eazy Bytes")
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customerName should be between 5 and 30")
    private String name;

    @Schema(description = "Email address of the customer", example = "tutor@eazyBytes.com")
    @NotEmpty(message = "Email address cannot be null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(description = "MobileNumber of the customer", example = "9999999999")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account details of the customer")
    private AccountsDTO accountsDTO;

    @Schema(description = "Loan details of the customer")
    private LoansDTO loansDTO;

    @Schema(description = "Card details of the customer")
    private CardsDTO cardsDTO;
}
