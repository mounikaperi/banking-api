package com.vaultsystem.customer.service;

import com.vaultsystem.customer.dto.CustomerBankDetailsDTO;
import com.vaultsystem.customer.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    void registerCustomer(CustomerDTO customer);
    List<CustomerBankDetailsDTO> fetchCustomerDetails(String userId);
}
