package de.alt.shop.validation.impl;

import de.alt.shop.validation.PasswordValidator;

public class PasswordNotEmptyValidatorImpl implements PasswordValidator {
    @Override
    public void validate(String password) {
        if (password == null || password.equals("") || password.equals(" ")) {  // валидируем (проверяем)  email
            throw new IllegalArgumentException("Password не может быть пустым");
        }
    }
}
