package com.bankingapi.loans.service;

import com.bankingapi.loans.dto.LoansDTO;

import java.util.List;

public interface ILoansService {
    void createLoan(LoansDTO loansDTO);
    List<LoansDTO> fetchLoans(String mobileNumber);
    boolean updateLoan(LoansDTO loansDTO);
    boolean deleteLoan(String loanNumber);
}
