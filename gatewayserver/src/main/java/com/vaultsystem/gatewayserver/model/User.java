package com.vaultsystem.gatewayserver.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name="user")
@Getter
@Setter
public class User {
    public User() {

    }
    public User(String userId, String email, String dwollaCustomerUrl, String dwollaCustomerId, String firstName, String lastName, String address, String city, String postalCode, LocalDateTime dateOfBirth) {
        this.userId = userId;
        this.email = email;
        this.dwollaCustomerUrl = dwollaCustomerUrl;
        this.dwollaCustomerId = dwollaCustomerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.dateOfBirth = dateOfBirth;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private long id;

    private String userId;

    private String email;

    private String password;

    private String dwollaCustomerUrl;

    private String dwollaCustomerId;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String postalCode;

    private LocalDateTime dateOfBirth;

    private String ssn;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;
}
