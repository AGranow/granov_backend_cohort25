package de.alt.shop.validation.impl;

import de.alt.shop.validation.EmailValidator;

public class EmailNotEmptyValidatorImpl implements EmailValidator {
    @Override
    public void validate(String email) {
        if (email == null || email.equals("") || email.equals(" ")) {  // валидируем (проверяем)  email
            throw new IllegalArgumentException("Email не может быть пустым");
        }

    }
}
