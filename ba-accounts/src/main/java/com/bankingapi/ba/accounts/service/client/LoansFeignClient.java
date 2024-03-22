package com.bankingapi.ba.accounts.service.client;

import com.bankingapi.ba.accounts.dto.LoansDTO;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ba-loans", fallback = LoansFallback.class)
public interface LoansFeignClient {
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<LoansDTO> fetchLoanDetails(@RequestParam String mobileNumber);
}
