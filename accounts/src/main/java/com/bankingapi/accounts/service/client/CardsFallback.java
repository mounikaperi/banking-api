package com.bankingapi.accounts.service.client;

import com.bankingapi.accounts.dto.CardsDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class CardsFallback implements CardsFeignClient {
    @Override
    public ResponseEntity<CardsDTO> fetchCardDetails(String correlationId, String mobileNumber) {
        CardsDTO cardsDTO = new CardsDTO();
        cardsDTO.setMessage("Unable to fetch card details of the customer. Please try again!!");
        return ResponseEntity.ok(cardsDTO);
    }
}
