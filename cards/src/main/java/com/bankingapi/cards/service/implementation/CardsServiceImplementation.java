package com.bankingapi.cards.service.implementation;

import com.bankingapi.cards.cards.CardsConstants;
import com.bankingapi.cards.dto.CardsDTO;
import com.bankingapi.cards.entity.Cards;
import com.bankingapi.cards.exception.CardAlreadyExistsException;
import com.bankingapi.cards.exception.ResourceNotFoundException;
import com.bankingapi.cards.mapper.CardsMapper;
import com.bankingapi.cards.repository.CardsRepository;
import com.bankingapi.cards.service.ICardsService;
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
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    public CardsDTO fetchCardDetails(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardsMapper.mapToCardsDTO(cards, new CardsDTO());
    }

    public boolean updateCard(CardsDTO cardsDTO) {
        Cards cards = cardsRepository.findByCardNumber(cardsDTO.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDTO.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDTO, cards);
        cardsRepository.save(cards);
        return true;
    }
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }
}
