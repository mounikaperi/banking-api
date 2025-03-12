package com.bankingapi.accounts.service.client;

import com.bankingapi.accounts.dto.CardsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class CardsFallback implements CardsFeignClient {
    @Override
    public ResponseEntity<CardsDTO> fetchCardDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
