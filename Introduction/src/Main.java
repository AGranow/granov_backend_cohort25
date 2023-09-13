import de.alt.shop.conntrollers.UsersController;
import de.alt.shop.repositories.UsersRepository;
import de.alt.shop.repositories.impl.UserRepositoryFileImpl;
import de.alt.shop.repositories.impl.UsersRepositoryListImpl;
import de.alt.shop.services.UsersService;
import de.alt.shop.services.impl.UsersServiceImpl;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // создаём сканер для считывания с консоли
        UsersRepository usersRepositoryList = new UsersRepositoryListImpl(); // создаём репезиторий
        UsersRepository usersRepositoryFile = new UserRepositoryFileImpl("users.file"); // создаём репезиторий
        UsersService usersService = new UsersServiceImpl(usersRepositoryFile);
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
                case "/exit":
                    isRun =false;
            }
        }
    }
}
