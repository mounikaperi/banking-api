package com.vaultsystem.transactions.service.client;

import com.vaultsystem.transactions.dto.CustomerBankDetailsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CustomerFallback implements CustomerFeignClient {
    @Override
    public ResponseEntity<List<CustomerBankDetailsDTO>> fetchAllUserBankAccounts(String userId) {
        CustomerBankDetailsDTO response = new CustomerBankDetailsDTO();
        response.setMessage("Unable to fetch the transactions of the user. Please try again!!");
        return ResponseEntity.ok(List.of(response));
    }
}