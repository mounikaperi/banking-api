package com.bankingapi.accounts.service.client;

import com.bankingapi.accounts.dto.CustomerBankDetailsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "customer",  fallback = CustomerFallback.class)
public interface CustomerFeignClient {
    @GetMapping(value = "/api/fetch-customer-details", consumes = "application/json")
    public ResponseEntity<List<CustomerBankDetailsDTO>> fetchCustomerDetails(@RequestParam String bankAccountId);
}
