package com.vaultsystem.customer.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name="customer")
@Getter
@Setter
public class Customer {
    public Customer() {

    }

    public Customer(String userId, String password, String emailAddress, String firstName, String lastName, String address, String city, String postalCode, LocalDateTime dateOfBirth) {
        this.userId = userId;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.dateOfBirth = dateOfBirth;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String customerId;

    private String userId;

    private String emailAddress;

    private String password;

    private String firstName;

    private String lastName;

    private String address;

    private String city;

    private String postalCode;

    private LocalDateTime dateOfBirth;

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
