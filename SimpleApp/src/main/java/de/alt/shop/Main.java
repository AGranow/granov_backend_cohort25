package de.alt.shop;

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

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // создаём сканер для считывания с консоли

        EmailValidator emailValidator = new EmailValidatorRegexImpl();

        PasswordValidator passwordValidator = new PasswordValidatorRegexImpl();

        UsersRepository usersRepositoryList = new UsersRepositoryListImpl(); // создаём репозиторий
        UsersRepository usersRepositoryFile = new UserRepositoryFileImpl("users.txt"); // создаём репозиторий
        UsersService usersService = new UsersServiceImpl(usersRepositoryFile,emailValidator,passwordValidator);

        UsersController usersController = new UsersController(scanner
                , usersService);

        boolean isRun = true;

        while (isRun) {
            String command = scanner.nextLine();

            switch (command) {
                case "/addUser":
                    usersController.addUser();
                    break;
                case "/users":
                    usersController.getUsers();
                    break;
                case "/userUpdate":
                    usersController.updateUser();
                    break;
                case "/exit":
                    isRun = false;
            }
        }
    }
}
