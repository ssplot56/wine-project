package com.project.wineproject.service.impl;

import com.project.wineproject.model.Card;
import com.project.wineproject.repository.CardRepository;
import com.project.wineproject.service.CardService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Override
    public Card save(Card card) {
        return cardRepository.save(card);
    }

    @Override
    public Card findById(Long id) {
        return cardRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Can't find card with id: " + id));
    }

    @Override
    public List<Card> findAll() {
        return cardRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        cardRepository.deleteById(id);
    }
}
