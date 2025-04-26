package com.vaultsystem.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Schema(name = "Customer", description = "Schema to validate user information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    @Schema(description = "customerId of the registered user", example="speri")
    private String customerId;

    @Schema(description = "customerId of the registered user", example="speri")
    private String userId;

    @NotEmpty(message = "FirstName of the user cannot be null or empty")
    @Schema(description = "FirstName of the registered user", example="Mounika")
    private String firstName;

    @NotEmpty(message = "LastName of the user cannot be null or empty")
    @Schema(description = "LastName of the registered user", example="Peri")
    private String lastName;

    @Schema(description = "Encrypted Password of the registered user", example="{bcrypt}Ayuio2Dgjolpuyqjihsnkhw23ssd")
    private String password;

    @Email
    @Schema(description = "email address of the registered user", example="speri@gmail.com")
    private String emailAddress;

    @NotEmpty(message = "Address of the user cannot be null or empty")
    @Schema(description = "address of the registered user", example="1916 Covemont Ct, Middleburg")
    private String address;

    @NotEmpty(message = "City cannot be null or empty")
    @Schema(description = "userId of the registered user", example="Florida(FL)")
    private String city;

    @NotEmpty(message = "PostalCode cannot be null or empty")
    @Schema(description = "PostalCode of the registered user", example="32068")
    private String postalCode;

    @NotEmpty(message = "DateOfBirth cannot be null or empty")
    @Schema(description = "DateOfBirth of the registered user", example="DD/MM/YYYY")
    @DateTimeFormat
    private LocalDateTime dateOfBirth;

}