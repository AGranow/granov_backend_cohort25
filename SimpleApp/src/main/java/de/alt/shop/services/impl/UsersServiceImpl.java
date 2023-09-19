package de.alt.shop.services.impl;

import de.alt.shop.models.User;
import de.alt.shop.repositories.UsersRepository;
import de.alt.shop.services.UsersService;

import java.util.List;

public class UsersServiceImpl implements UsersService {


    private final UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public User addUser(String email, String password) {
        if (email == null || email.equals("") || email.equals(" ")) {  // валидируем (проверяем)  email
            throw new IllegalArgumentException("Email не может быть пустым");
        }

        if (password == null || password.equals("") || password.equals(" ")) {  // валидируем (проверяем)  email
            throw new IllegalArgumentException("Password не может быть пустым");
        }

        User existedUser = usersRepository.findOneByEmail(email); // находим пользователя по email

        if (existedUser != null) {
            throw new IllegalArgumentException("Пользователь с таким именем уже есть");
        }

        User user = new User(email, password); // создаём пользователя

        usersRepository.save(user);  //сохранение пользователя в хранилище
        return user;  // возвращаем пользователя как результат
    }

    @Override
    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public void updateUser(Long idForUpgrade, String newEmail, String newPassword) {
        // нужно получить этого пользователя по ID

        //простой вариант - воспользоваться findAll
        List<User> users = usersRepository.findAll();

        User userForUpdate = null;

        for (User user : users) {
            if (user.getId().equals(idForUpgrade)) {
                // запоминаем этого пользователя
                userForUpdate = user;
                break;
            }
        }

        // если пользователя не нашли выкинуть exception

        if (userForUpdate == null){
            throw new IllegalArgumentException("User with id <" + idForUpgrade +"> not found");
        }

        // проверим корректность данных на обновление
        if (!newEmail.isBlank()){
            // если пользователь не указал email как пробел
            userForUpdate.setEmail(newEmail);
        }

        if (!newPassword.isBlank()){
            // если пользователь не указал password как пробел
            userForUpdate.setPassword(newPassword);
        }

//   обновить данные в файле
        usersRepository.update(userForUpdate);

    }
}

