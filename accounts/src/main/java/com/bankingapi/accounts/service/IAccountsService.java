package com.bankingapi.accounts.service;

import com.bankingapi.accounts.dto.CustomerDTO;

public interface IAccountsService {
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDTO customerDTO);
    boolean deleteAccount(String mobileNumber);
}
