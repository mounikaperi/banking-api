package com.vaultsystem.customer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(name = "Customer", description = "Schema to validate customer information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDTO {

    @Schema(description = "userId of the registered customer", example="speri")
    private String userId;

    @NotEmpty(message = "FirstName of the customer cannot be null or empty")
    @Schema(description = "FirstName of the registered customer", example="Mounika")
    private String firstName;

    @NotEmpty(message = "LastName of the customer cannot be null or empty")
    @Schema(description = "LastName of the registered customer", example="Peri")
    private String lastName;

    @Schema(description = "Encrypted Password of the registered customer", example="{bcrypt}Ayuio2Dgjolpuyqjihsnkhw23ssd")
    private String password;

    @Email
    @Schema(description = "email address of the registered customer", example="speri@gmail.com")
    private String emailAddress;

    @NotEmpty(message = "Address of the customer cannot be null or empty")
    @Schema(description = "address of the registered customer", example="1916 Covemont Ct, Middleburg")
    private String address;

    @NotEmpty(message = "City cannot be null or empty")
    @Schema(description = "userId of the registered customer", example="Florida(FL)")
    private String city;

    @NotEmpty(message = "PostalCode cannot be null or empty")
    @Schema(description = "PostalCode of the registered customer", example="32068")
    private String postalCode;

    @NotEmpty(message = "DateOfBirth cannot be null or empty")
    @Schema(description = "DateOfBirth of the registered customer", example="DD/MM/YYYY")
    @DateTimeFormat
    private LocalDateTime dateOfBirth;


    @NotEmpty(message = "List of Banks and respective accountId's the customer is affiliated to cannot be null or empty")
    @Schema(description = "List of Banks and respective accountId's of the registered customer", example="DD/MM/YYYY")
    private List<BankDTO> banksDTOList;

}