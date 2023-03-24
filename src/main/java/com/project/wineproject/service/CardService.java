package com.project.wineproject.service;

import com.project.wineproject.model.Card;
import java.util.List;

public interface CardService {
    Card save(Card card);

    Card findById(Long id);

    List<Card> findAll();

    void delete(Long id);
}
