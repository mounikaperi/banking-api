package com.bankingapi.ba.accounts.service;

import com.bankingapi.ba.accounts.dto.CustomerDetailsDTO;

public interface ICustomersService {
    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber);
}
