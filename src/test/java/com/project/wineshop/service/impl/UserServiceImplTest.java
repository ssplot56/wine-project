package com.project.wineshop.service.impl;

import com.project.wineshop.model.User;
import com.project.wineshop.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    private static final String EMAIL = "user@mail.com";
    private static final String PHONE_NUMBER = "+380111111111";
    private static final String ANOTHER_PHONE_NUMBER = "+380111111112";
    private static User userBob;
    private static List<User> userList;

    @BeforeAll
    static void beforeAll() {
        userBob = new User();
        userBob.setEmail(EMAIL);
        userBob.setPhoneNumber(PHONE_NUMBER);

        User userAlice = new User();
        userAlice.setEmail("alice@mail.com");
        userAlice.setPhoneNumber("1");

        User userSwen = new User();
        userSwen.setEmail("swen@mail.com");
        userSwen.setPhoneNumber("2");

        User userJohn = new User();
        userJohn.setEmail("john@mail.com");
        userJohn.setPhoneNumber("3");

        User userNull = new User();
        userNull.setEmail("null@mail.com");
        userNull.setPhoneNumber(null);

        userList = new ArrayList<>();
        userList.add(userBob);
        userList.add(userAlice);
        userList.add(userSwen);
        userList.add(userJohn);
        userList.add(userNull);
    }

    @Test
    void shouldReturnTrueWhenUserAlreadyHasThisNumber() {
        Mockito.when(userRepository.findUserByEmail(EMAIL)).thenReturn(userBob);
        boolean actual = userService.phoneNumberIsAvailable(PHONE_NUMBER, EMAIL);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnTrueWhenPhoneNumberIsUnique() {
        Mockito.when(userRepository.findUserByEmail(EMAIL)).thenReturn(userBob);
        Mockito.when(userService.findAll()).thenReturn(userList);
        boolean actual = userService.phoneNumberIsAvailable(ANOTHER_PHONE_NUMBER, EMAIL);
        Assertions.assertTrue(actual);
    }

    @Test
    void shouldReturnFalseWhenPhoneNumberIsNotUnique() {
        Mockito.when(userRepository.findUserByEmail(EMAIL)).thenReturn(userBob);
        Mockito.when(userService.findAll()).thenReturn(userList);
        boolean actual = userService.phoneNumberIsAvailable("1", EMAIL);
        Assertions.assertFalse(actual);
    }

    @Test
    void shouldReturnTrueWhenPhoneNumberIsNull() {
        userBob.setPhoneNumber(null);
        Mockito.when(userRepository.findUserByEmail(EMAIL)).thenReturn(userBob);
        Mockito.when(userService.findAll()).thenReturn(userList);
        boolean actual = userService.phoneNumberIsAvailable(null, EMAIL);
        Assertions.assertTrue(actual);
    }
}
