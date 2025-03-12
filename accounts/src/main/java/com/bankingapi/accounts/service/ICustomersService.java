package com.bankingapi.accounts.service;

import com.bankingapi.accounts.dto.CustomerDetailsDTO;

public interface ICustomersService {
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId);
}
