package ru.aston.validators;

import ru.aston.entity.User;
import ru.aston.exceptions.ValidationException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {

    private final String EMAIL_REGEX = "\\w+@\\w+\\.\\w+";
    private final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean emailValidator(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public void validate(User user) {

        List<String> errors = new ArrayList<>();

        if (user.getUsername() == null || user.getUsername().isBlank()) errors.add("UserName must not be empty");
        if (user.getPassword() == null || user.getPassword().isBlank()) errors.add("Password must not be empty");
        if (user.getEmail() == null || user.getEmail().isBlank()) errors.add("Email must not be empty");
        if (!emailValidator(user.getEmail())) errors.add("The email address is invalid");
        if (!errors.isEmpty()) throw new ValidationException(errors);
    }

}
