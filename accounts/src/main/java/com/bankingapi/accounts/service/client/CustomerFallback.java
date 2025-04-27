package com.bankingapi.accounts.service.client;

import com.bankingapi.accounts.dto.CustomerBankDetailsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerFallback implements CustomerFeignClient {
    @Override
    public ResponseEntity<List<CustomerBankDetailsDTO>> fetchCustomerDetails(String bankAccountId) {
        CustomerBankDetailsDTO customerBankDetailsDTO = new CustomerBankDetailsDTO();
        customerBankDetailsDTO.setMessage("Unable to fetch details of the customer. Please try again!!!");
        return ResponseEntity.status(HttpStatus.OK).body(List.of(customerBankDetailsDTO));
    }
}