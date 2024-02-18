package com.bankingapi.ba.accounts.service;

import com.bankingapi.ba.accounts.dto.CustomerDTO;

public interface IAccountsService {
    void createAccount(CustomerDTO customerDTO);
    CustomerDTO fetchAccount(String mobileNumber);
    boolean updateAccount(CustomerDTO customerDTO);
    boolean deleteAccount(String mobileNumber);
}
