package de.alt.shop.config;

import de.alt.shop.conntrollers.UsersController;
import de.alt.shop.repositories.UsersRepository;
import de.alt.shop.repositories.impl.UserRepositoryFileImpl;
import de.alt.shop.repositories.impl.UsersRepositoryListImpl;
import de.alt.shop.services.UsersService;
import de.alt.shop.services.impl.UsersServiceImpl;
import de.alt.shop.validation.EmailValidator;
import de.alt.shop.validation.PasswordValidator;
import de.alt.shop.validation.impl.EmailNotEmptyValidatorImpl;
import de.alt.shop.validation.impl.EmailValidatorRegexImpl;
import de.alt.shop.validation.impl.PasswordNotEmptyValidatorImpl;
import de.alt.shop.validation.impl.PasswordValidatorRegexImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfig {
    @Bean
    public PasswordValidator passwordValidatorNotEmpty() {
        return new PasswordNotEmptyValidatorImpl();
    }

    @Bean
    public PasswordValidator passwordValidatorRegex() {
        return new PasswordValidatorRegexImpl();
    }

    @Bean
    public EmailValidator emailValidatorNotEmpty() {
        return new EmailNotEmptyValidatorImpl();
    }

    @Bean
    public EmailValidator emailValidatorRegex() {
        return new EmailValidatorRegexImpl();
    }

    @Bean
    public UsersRepository usersRepositoryList() {
        return new UsersRepositoryListImpl();
    }

    @Bean
    public UsersRepository usersRepositoryFile () {
        return new UserRepositoryFileImpl("users.txt");
    }

    @Bean
    public UsersService usersService(UsersRepository usersRepositoryFile,
                                     EmailValidator emailValidatorRegex,
                                     PasswordValidator passwordValidatorRegex){
        return new UsersServiceImpl(usersRepositoryFile,emailValidatorRegex,passwordValidatorRegex);
    }

    @Bean
    public Scanner scanner(){
        return  new Scanner(System.in);
    }

    @Bean
    public UsersController usersController(Scanner scanner ,UsersService usersService){
        return new UsersController(scanner,usersService);
    }
}
