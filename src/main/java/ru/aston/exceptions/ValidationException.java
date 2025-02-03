package ru.aston.exceptions;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ValidationException extends RuntimeException{
    private List<String> errorFieldsMessages;

    public ValidationException(List<String> errorFieldsMessages) {
        super(String.join(", ", errorFieldsMessages));
        this.errorFieldsMessages = errorFieldsMessages;
    }

}
