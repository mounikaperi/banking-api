package com.vaultsystem.transactions.service.client;

import com.vaultsystem.transactions.dto.CustomerBankDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name="customer", fallback = CustomerFallback.class)
public interface CustomerFeignClient {
    @GetMapping(value = "/api/fetchAllUserBankAccounts", consumes = "application/json")
    public ResponseEntity<List<CustomerBankDetailsDTO>> fetchAllUserBankAccounts(@RequestParam String userId);
}
