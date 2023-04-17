package com.project.wineshop.exception;

public class UserWithSuchPhoneNumberExistException extends RuntimeException{
    public UserWithSuchPhoneNumberExistException(String message) {
        super(message);
    }
}
