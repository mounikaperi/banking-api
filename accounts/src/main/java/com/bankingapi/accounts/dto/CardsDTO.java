package com.bankingapi.accounts.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name = "Cards", description = "Schema to hold Card information")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardsDTO {

    @NotEmpty(message = "MobileNumber cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile Number must be 10 digits")
    @Schema(description = "Mobile Number of the customer", example = "9876540321")
    private String mobileNumber;

    @NotEmpty(message = "Card Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
    @Schema(description = "CardNumber of the customer", example = "100646578912")
    private String cardNumber;

    @NotEmpty(message = "CardType cannot be null or empty")
    @Schema(description = "Type of the card", example = "Credit Card")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    @Schema(description = "Total amount limit available against a card", example = "100000")
    private Integer totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    @Schema(description = "Total amount used by the customer", example = "1000")
    private Integer amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    @Schema(description = "Total available amount against a card", example = "99000")
    private Integer availableAmount;

    @Schema(description = "Display Message in case cards microservice is down", example="Unable to fetch card details of customer. Please try again!!")
    private String message;
}
