package com.bankingapi.loans.service;

import com.bankingapi.loans.dto.LoansDTO;

public interface ILoansService {
    void createLoan(String mobileNumber);
    LoansDTO fetchLoan(String mobileNumber);
    boolean updateLoan(LoansDTO loansDTO);
    boolean deleteLoan(String mobileNumber);
}
