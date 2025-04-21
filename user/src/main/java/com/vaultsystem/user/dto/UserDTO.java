package com.vaultsystem.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Schema(name = "User", description = "Schema to validate user information")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {

    @Schema(description = "userId of the registered user", example="speri")
    private String userId;

    @Email
    @Schema(description = "email address of the registered user", example="speri@gmail.com")
    private String email;

    @Schema(description = "Encrypted Password of the registered user", example="{bcrypt}Ayuio2Dgjolpuyqjihsnkhw23ssd")
    private String password;

    private String dwollaCustomerUrl;

    private String dwollaCustomerId;

    @NotEmpty(message = "FirstName of the user cannot be null or empty")
    @Schema(description = "FirstName of the registered user", example="Mounika")
    private String firstName;

    @NotEmpty(message = "LastName of the user cannot be null or empty")
    @Schema(description = "LastName of the registered user", example="Peri")
    private String lastName;

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

    /*
    The valid SSN (Social Security Number) must satisfy the following conditions:

    It should have 9 digits.
    It should be divided into 3 parts by hyphen (-).
    The first part should have 3 digits and should not be 000, 666, or between 900 and 999.
    The second part should have 2 digits and it should be from 01 to 99.
    The third part should have 4 digits and it should be from 0001 to 9999.
     */
    @NotEmpty(message = "Social Security Number of the user cannot be empty")
    @Schema(description = "SocialSecurityNumber of the registered user", example="856-45-6789")
    @Pattern(regexp = "^(?!000|666|9\\d{2})\\d{3}-?(?!00)\\d{2}-?(?!0000)\\d{4}$", message = "Invalid SSN format")
    private String ssn;

}