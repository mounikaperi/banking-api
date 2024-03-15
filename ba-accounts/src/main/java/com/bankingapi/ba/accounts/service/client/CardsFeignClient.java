package com.bankingapi.ba.accounts.service.client;

import com.bankingapi.ba.accounts.dto.CardsDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ba-cards")
public interface CardsFeignClient {
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDTO> fetchCardDetails(@RequestParam String mobileNumber);
}
