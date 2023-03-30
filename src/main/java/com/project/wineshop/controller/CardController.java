package com.project.wineshop.controller;

import com.project.wineshop.dto.request.CardRequestDto;
import com.project.wineshop.dto.response.CardResponseDto;
import com.project.wineshop.model.Card;
import com.project.wineshop.model.User;
import com.project.wineshop.service.CardService;
import com.project.wineshop.service.UserService;
import com.project.wineshop.service.mapper.impl.CardMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
public class CardController {
    private final UserService userService;
    private final CardService cardService;
    private final CardMapper cardMapper;

    public CardController(UserService userService,
                          CardService cardService,
                          CardMapper cardMapper) {
        this.userService = userService;
        this.cardService = cardService;
        this.cardMapper = cardMapper;
    }

    @PostMapping("/{id}/cards")
    public ResponseEntity<CardResponseDto> addCardToUser(@PathVariable Long id,
                                                         CardRequestDto cardRequestDto) {
        User user = userService.findById(id);
        Card card = cardService.save(cardMapper.mapToModel(cardRequestDto));
        List<Card> cards = user.getCards();
        cards.add(card);
        user.setCards(cards);
        userService.save(user);
        return ResponseEntity.ok(cardMapper.mapToDto(card));
    }

    @GetMapping("/{id}/cards")
    public ResponseEntity<List<CardResponseDto>> findAllUsersCards(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id).getCards().stream()
                .map(cardMapper::mapToDto).toList(), HttpStatus.OK);
    }

    @DeleteMapping("{id}/cards")
    public ResponseEntity<String> deleteCards(@PathVariable Long id) {
        User user = userService.findById(id);
        user.setCards(new ArrayList<>());
        userService.save(user);
        return ResponseEntity.ok("User cards have been deleted.");
    }
}
