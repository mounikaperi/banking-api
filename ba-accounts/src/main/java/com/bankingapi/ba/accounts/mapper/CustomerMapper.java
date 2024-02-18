package com.bankingapi.ba.accounts.mapper;

import com.bankingapi.ba.accounts.dto.CustomerDTO;
import com.bankingapi.ba.accounts.entities.Customer;

public class CustomerMapper {
    public static CustomerDTO mapToCustomerDTO(Customer customer, CustomerDTO customerDTO) {
        customerDTO.setName(customer.getName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setMobileNumber(customer.getMobileNumber());
        return customerDTO;
    }

    public static Customer mapToCustomer(CustomerDTO customerDTO, Customer customer) {
        customer.setName(customerDTO.getName());
        customer.setEmail(customer.getEmail());
        customer.setMobileNumber(customer.getMobileNumber());
        return customer;
    }
}
