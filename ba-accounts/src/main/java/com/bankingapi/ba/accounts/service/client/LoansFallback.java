package com.bankingapi.ba.accounts.service.client;

import com.bankingapi.ba.accounts.dto.LoansDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient {

    @Override
    public ResponseEntity<LoansDTO> fetchLoanDetails(String mobileNumber) {
        return null;
    }
}
