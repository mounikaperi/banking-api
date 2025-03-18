package com.bankingapi.cards.service;

import com.bankingapi.cards.dto.CardsDTO;

import java.util.List;

public interface ICardsService {
    void createCard(CardsDTO cardsDTO);
    List<CardsDTO> fetchCardDetails(String mobileNumber);
    boolean updateCard(CardsDTO cardsDTO);
}
