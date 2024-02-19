package com.bankingapi.bacards.service;

import com.bankingapi.bacards.dto.CardsDTO;

public interface ICardsService {
    void createCard(String mobileNumber);
    CardsDTO fetchCardDetails(String mobileNumber);
    boolean updateCard(CardsDTO cardsDTO);
    boolean deleteCard(String mobileNumber);
}
