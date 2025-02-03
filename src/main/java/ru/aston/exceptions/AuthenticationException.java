package ru.aston.exceptions;

/**
 * Подготовлено к добавлению возможности авторизоваться в приложении..
 */

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message) {
        super(message);
    }
}
