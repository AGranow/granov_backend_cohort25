package de.alt.shop.repositories.impl;

import de.alt.shop.models.User;
import de.alt.shop.repositories.UsersRepository;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class UserRepositoryFileImpl implements UsersRepository {

    private final String fileName;
    private Long generatedId = 1L;

    public UserRepositoryFileImpl(String fileName) {
        this.fileName = fileName;

    }

    @Override
    public User findById() {
        return null;
    }

    @Override
    public List<User> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) { // открываем файл для чтения
            return reader.lines()
                    .map(line -> line.split("#")) //разбиваем каждую строку на массив из нескольких строк по разделителю
                    .map(parsed -> new User(Long.parseLong(parsed[0]), parsed[1], parsed[2])) // превращаем строку в пользователя
                    .collect(Collectors.toList()); // преобразовали в список
        } catch (IOException e) {  // если была ошибка с файлом то сообщаем об этом и останавливаем приложение
            throw new IllegalStateException("Проблемы с чтением из файла " + e.getMessage());
        }
    }

    @Override
    public void save(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            user.setId(generatedId);// присваиваем ID пользователю
            writer.write(user.getId() + "#" + user.getEmail() + "#" + user.getPassword());  //  записываем его в файл
            writer.newLine(); // создаём новую строку

        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с записью в файл: " + e.getMessage());
        }
        generatedId++;
    }


    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(User model) {
        // на вход получили пользователя, чтобы перезаписать его в файл
        // для этого переписать весь файл
        //сначала прочитать весь файл, положить в него пользователя и перезаписать
        List<User> users = findAll();
        //удалим пользователя со старыми данными
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(model.getId())) {
                users.remove(i); // удаляем из списка
                break;
            }
        }

        users.add(model);
        // записать список в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))){

            for (User user : users) {
                writer.write(user.getId() + "#" + user.getEmail() + "#" + user.getPassword()); // записываем его в файл
                writer.newLine(); // создаем новую строку
            }

        } catch (IOException e) {
            throw new IllegalStateException("Проблемы с записью в файл: " + e.getMessage());
        }

    }

    @Override
    public User findOneByEmail(String email) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split("#")) //разбиваем каждую строку на массив из нескольких строк по разделителю
                    .filter(parsed -> parsed[1].equals(email))// находим строку с таким же email
                    .findFirst()  // берём первую строку которая нам подошла
                    .map(parsed -> new User(Long.parseLong(parsed[0]), parsed[1], parsed[2])) // превращаем строку в пользователя
                    .orElse(null);
        } catch (IOException e) {  // если была ошибка с файлом то сообщаем об этом и останавливаем  приложение
            throw new IllegalStateException("Проблемы с чтением из файла " + e.getMessage());
        }
    }
}
