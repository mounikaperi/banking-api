package com.bankingapi.accounts.service.client;

import com.bankingapi.accounts.dto.LoansDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="loans",fallback = LoansFallback.class)
public interface LoansFeignClient {

    @GetMapping(value = "/api/fetch",consumes = "application/json")
    public ResponseEntity<LoansDTO> fetchLoanDetails(@RequestHeader("eazybank-correlation-id")
                                                     String correlationId, @RequestParam String mobileNumber);

}
