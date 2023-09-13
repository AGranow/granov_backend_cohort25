package de.alt.shop.conntrollers;

import de.alt.shop.models.User;
import de.alt.shop.services.UsersService;

import java.util.List;
import java.util.Scanner;

public class UsersController {

    private final Scanner scanner;

    private final UsersService usersService;


    public UsersController(Scanner scanner, UsersService usersService) {
        this.scanner = new Scanner(System.in);
        this.usersService = usersService;


    }


    public void addUser(){
        System.out.println("Введите емейл");
        String email = scanner.nextLine();

        System.out.println("Введите пароль");
        String password = scanner.nextLine();

        User user = usersService.addUser(email , password);

        System.out.println(user);

    }

    public void getUsers(){  // метод для получения всех пользователей
        List<User> users = usersService.getAllUsers(); // запрашиваем у бизнес-логики всех пользователей
        System.out.println(users);

    }
}
