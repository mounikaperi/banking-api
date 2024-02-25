package com.bankingapi.baloans.service;

import com.bankingapi.baloans.dto.LoansDTO;
import org.springframework.stereotype.Service;

public interface ILoansService {
    void createLoan(String mobileNumber);
    LoansDTO fetchLoan(String mobileNumber);
    boolean updateLoan(LoansDTO loansDTO);
    boolean deleteLoan(String mobileNumber);
}
