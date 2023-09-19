package de.alt.shop.repositories.impl;

import static org.junit.jupiter.api.Assertions.*;

import de.alt.shop.models.User;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.Arrays;
import java.util.List;


@DisplayName("UsersRepositoryFileImpl is works...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class) // убирает _
class UserRepositoryFileImplTest {

    private static final String TEMP_USERS_FILE_NAME = "users_test.txt";
    private UserRepositoryFileImpl userRepository;


    // Нужно, что бы перед каждым тестом создавался временный файл
    @BeforeEach
    public void setUp() throws IOException {
        File file = new File(TEMP_USERS_FILE_NAME);

        createNewFileForTest(file);

        userRepository = new UserRepositoryFileImpl(TEMP_USERS_FILE_NAME);
    }


    @DisplayName("save():")
    @Nested // Nested -внутренний * аннотация помечает тесты во вложенных классах
    class SaveTest {
        @Test
        public void writes_correct_line_to_file() throws IOException {
            User user = new User("user@ait-tr.de", "qwerty007"); // создам пользователя

            userRepository.save(user);

            String expected = "1#user@ait-tr.de#qwerty007";

            BufferedReader reader = new BufferedReader(new FileReader(TEMP_USERS_FILE_NAME));

            String actual = reader.readLine(); // считываем строку с файла после вызова метода save

            reader.close(); // закрыли файл после чтения

            assertEquals(expected, actual); // сравниваем то что ожидаем с тем что записано в файл

        }

    }

    //  нужно удалять файл после каждого вызова теста

    @AfterEach
    public void tearDown() throws Exception {

        deleteFileAfterTest();
    }



    @DisplayName("findAll: ")
    @Nested // Nested -внутренний * аннотация помечает тесты во вложенных классах
    class FindAll {

        @Test
        public void returns_correct_list_of_users() throws Exception {
            // запишем в файл список людей
            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_USERS_FILE_NAME));

            writer.write("1#user1@ait-tr.de#qwerty007");
            writer.newLine();

            writer.write("2#user2@ait-tr.de#qwerty008");
            writer.newLine();

            writer.close(); // закрыть файл после записи

            List<User> expected = Arrays.asList( // формируем список пользователей
                    new User(1L, "user1@ait-tr.de", "qwerty007"),
                    new User(2L, "user2@ait-tr.de", "qwerty008"));

            List<User> actual = userRepository.findAll(); // получаем фактический список пользователей

            assertEquals(expected, actual);

        }


    }

    private static void createNewFileForTest(File file) throws IOException {
        // проверим вдруг файл уже есть
        deleteIfExists(file);

        // файла точно нет, но можно создать
        boolean result = file.createNewFile();

        if (!result) { // если не удалось создать файл выкидываем исключение
            throw new IllegalStateException("Problems with file create");
        }
    }

    private static void deleteIfExists(File file) {
        if (file.exists()) {

            // Тогда нужно его удалить
            boolean result = file.delete();

            if (!result) {
                throw new IllegalStateException("Problems with file delete");
            }
        }
    }

    private void deleteFileAfterTest() {
        File file = new File(TEMP_USERS_FILE_NAME);

        deleteIfExists(file);
    }

}