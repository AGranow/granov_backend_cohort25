package de.alt.shop.validation.impl;

import de.alt.shop.validation.PasswordValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidatorRegexImpl implements PasswordValidator {

    private static final String REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";

    @Override
    public void validate(String password) {
        Pattern pattern = Pattern.compile(REGEX); // создаем шаблон
        Matcher matcher = pattern.matcher(password); // создаем проверяльщика

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Пароль слабый");
        }
    }
}
