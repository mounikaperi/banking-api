package com.bankingapi.accounts.service;

import com.bankingapi.accounts.dto.CustomerBankDetailsDTO;

public interface ICustomersService {
    CustomerBankDetailsDTO fetchCustomerDetails(String bankAccountId);
}
