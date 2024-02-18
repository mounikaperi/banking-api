package com.bankingapi.bacards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(name="Cards", description="Schema to hold Card Information")
@Data
public class CardsDTO {

    @NotEmpty(message = "MobileNumber cannot be null or empty")
    @Pattern(regexp="(^$|[0-9]{10})", message = "MobileNumber must be 10 digits")
    @Schema(description = "Mobile Number of Customer", example = "9999999999")
    private String mobileNumber;

    @NotEmpty(message = "CardNumber cannot be a null or empty")
    @Pattern(regexp="$|[0-9]{12}", message = "CardNumber must be 12 digits")
    @Schema(description = "Card Number of the customer", example = "100646933123")
    private String cardNumber;

    @NotEmpty(message = "CardType cannot be null or empty")
    @Schema(description = "Type of the card", example = "Credit Card")
    private String cardType;

    @Positive(message = "Total CardLimit should be greater than zero")
    @Schema(description = "Total amount limit available against the card", example = "100000")
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be equal or greater than zero")
    @Schema(description = "Total amount used by a customer", example = "1000")
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    @Schema(description = "Total amount available against a card", example = "99000")
    private int availableAmount;
}
