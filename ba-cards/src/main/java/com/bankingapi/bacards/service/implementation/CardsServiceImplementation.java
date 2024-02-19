package com.bankingapi.bacards.service.implementation;

import com.bankingapi.bacards.entity.Cards;
import com.bankingapi.bacards.exception.CardAlreadyExistsException;
import com.bankingapi.bacards.repository.CardsRepository;
import com.bankingapi.bacards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImplementation implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with the given mobile number" + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }

    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        newCard.setMobileNumber(mobileNumber);
        long generatedCardNumber = 1000000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(generatedCardNumber));
        newCard.setCardType(CardsContants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }
}
