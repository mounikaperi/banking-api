package com.bankingapi.accounts.service.client;

import com.bankingapi.accounts.constants.AccountsConstants;
import com.bankingapi.accounts.dto.LoansDTO;
import com.bankingapi.accounts.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public ResponseEntity<List<LoansDTO>> fetchLoanDetails(String correlationId, String mobileNumber) {
        LoansDTO loansDTO = new LoansDTO();
        loansDTO.setMessage("Unable to fetch loan details of the customer. Please try again!!!");
        return ResponseEntity.status(HttpStatus.OK).body(List.of(loansDTO));
    }
}