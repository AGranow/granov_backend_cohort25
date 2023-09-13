package de.alt.shop.repositories.impl;

import de.alt.shop.models.User;
import de.alt.shop.repositories.UsersRepository;

import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryListImpl implements UsersRepository {

    private final List<User> users = new ArrayList(); // список пользователей
    public Long generatedId = 1L; //

    @Override
    public User findById() {
        return null;

    }

    @Override
    public List<User> findAll() {

        return new ArrayList<>(users) ;
    }

    @Override
    public void save(User user) {
        users.add(user); // положили пользователя в список
        user.setId(generatedId); // задали этому пользователю идентификатор
        generatedId++; // значение идентификатора увеличили на один для следующего пользователя.
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(User model) {

    }

    @Override
    public User findOneByEmail(String email) {
//        for (User user : findAll()) {
//            if (user.getEmail().equals(email)) return user;
//        }
//        return null;

        return  users.stream()
                .filter(user -> user.getEmail().equals(email)).findFirst().orElse(null) ;

    }
}
