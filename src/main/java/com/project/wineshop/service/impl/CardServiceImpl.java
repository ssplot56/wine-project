package com.project.wineshop.service.impl;

import com.project.wineshop.model.Card;
import com.project.wineshop.repository.CardRepository;
import com.project.wineshop.service.CardService;
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
    public Card update(Long id, Card card) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        cardRepository.deleteById(id);
    }
}
