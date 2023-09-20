package de.alt.shop;

import de.alt.shop.config.AppConfig;
import de.alt.shop.conntrollers.UsersController;
import de.alt.shop.repositories.UsersRepository;
import de.alt.shop.services.UsersService;
import de.alt.shop.services.impl.UsersServiceImpl;
import de.alt.shop.validation.EmailValidator;
import de.alt.shop.validation.PasswordValidator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {

        // создаём Spring- контейнер
        // передаём ему AppConfig
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        Scanner scanner = applicationContext.getBean(Scanner.class);

        UsersController usersController = applicationContext.getBean(UsersController.class);

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
