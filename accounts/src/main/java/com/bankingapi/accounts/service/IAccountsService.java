package com.bankingapi.accounts.service;

import com.bankingapi.accounts.dto.BankAccountDetailsDTO;

import java.util.List;

public interface IAccountsService {
    List<BankAccountDetailsDTO> getAllUserBankAccountDetails(String userId);
}
