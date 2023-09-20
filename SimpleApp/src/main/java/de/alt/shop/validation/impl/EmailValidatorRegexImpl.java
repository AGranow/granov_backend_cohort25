package de.alt.shop.validation.impl;

import de.alt.shop.validation.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidatorRegexImpl implements EmailValidator {

    private static final String REGEX = "^(?=.{1,256}$)[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]" +
            "+(?:\\.[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[A-Za-z0-9]" +
            "(?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z]{2,}$";

    @Override
    public void validate(String email) {
        Pattern pattern = Pattern.compile(REGEX);// создаём "шаблон" по регулярному выражению
        Matcher matcher = pattern.matcher(email);// cоздаём объект, который проверяет
        // совпадение регулярного выражения(patern -a)  c вашей строкой email
        // который проверяет совпадение регулярного выражения (pattern-а) c вашей строкой email
        if (!matcher.matches()) { // если совпадения между регулярным выражением и строкой нет
            throw new IllegalArgumentException("Email не соответствует формату");
        }
    }
}
