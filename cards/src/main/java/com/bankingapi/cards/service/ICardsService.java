package com.bankingapi.cards.service;

import com.bankingapi.cards.dto.CardsDTO;

public interface ICardsService {
    void createCard(String mobileNumber);
    CardsDTO fetchCardDetails(String mobileNumber);
    boolean updateCard(CardsDTO cardsDTO);
    boolean deleteCard(String mobileNumber);
}
