package com.bankingapi.accounts.service.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CardsFallback implements CardsFeignClient {
    @Override
    public ResponseEntity<List<CardsDTO>> fetchCardDetails(String correlationId, String mobileNumber) {
        CardsDTO cardsDTO = new CardsDTO();
        cardsDTO.setMessage("Unable to fetch card details of the customer. Please try again!!");
        return ResponseEntity.ok(List.of(cardsDTO));
    }
}
