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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class CardsServiceImplementation implements ICardsService {

    private CardsRepository cardsRepository;

    @Override
    public void createCard(CardsDTO cardsDTO) {
        String mobileNumber = cardsDTO.getMobileNumber();
        List<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
        if (cards.contains(cardsDTO.getCardType())) {
            throw new CardAlreadyExistsException("Card already registered with the given mobile number" + mobileNumber);
        }
        cardsRepository.save(createNewCard(cardsDTO));
    }


    private Cards createNewCard(CardsDTO cardsDTO) {
        Cards newCard = new Cards();
        newCard.setMobileNumber(cardsDTO.getMobileNumber());
        long generatedCardNumber = 1000000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(generatedCardNumber));
        newCard.setCardType(cardsDTO.getCardType());
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        newCard.setActive(true);
        return newCard;
    }

    public List<CardsDTO> fetchCardDetails(String mobileNumber) {
        List<Cards> cards = cardsRepository.findByMobileNumber(mobileNumber);
        if (!cards.isEmpty()) {
            Stream<CardsDTO> mappedCards = cards.stream().map((currentCard) -> CardsMapper.mapToCardsDTO(currentCard, new CardsDTO()));
            return mappedCards.toList();
        }
        return List.of();
    }

    public boolean updateCard(CardsDTO cardsDTO) {
        Cards cards = cardsRepository.findByCardNumber(cardsDTO.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDTO.getCardNumber())
        );
        CardsMapper.mapToCards(cardsDTO, cards);
        cardsRepository.save(cards);
        return true;
    }
}
