package com.project.wineshop.service.mapper.impl;

import com.project.wineshop.dto.request.CardRequestDto;
import com.project.wineshop.dto.response.CardResponseDto;
import com.project.wineshop.model.Card;
import com.project.wineshop.service.mapper.RequestDtoMapper;
import com.project.wineshop.service.mapper.ResponseDtoMapper;
import org.springframework.stereotype.Component;

@Component
public class CardMapper implements RequestDtoMapper<Card, CardRequestDto>,
        ResponseDtoMapper<Card, CardResponseDto> {
    @Override
    public Card mapToModel(CardRequestDto cardRequestDto) {
        Card card = new Card();
        card.setCardNumber(cardRequestDto.getCardNumber());
        card.setExpiredMonth(cardRequestDto.getExpiredMonth());
        card.setExpiredYear(cardRequestDto.getExpiredYear());
        card.setCvv(card.getCvv());
        return card;
    }

    @Override
    public CardResponseDto mapToDto(Card card) {
        CardResponseDto responseDto = new CardResponseDto();
        responseDto.setId(card.getId());
        responseDto.setCardNumber(card.getCardNumber());
        return responseDto;
    }
}
