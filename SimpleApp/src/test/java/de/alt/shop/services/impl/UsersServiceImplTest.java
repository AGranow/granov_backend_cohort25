package de.alt.shop.services.impl;

import de.alt.shop.models.User;
import de.alt.shop.repositories.UsersRepository;
import de.alt.shop.validation.EmailValidator;
import de.alt.shop.validation.PasswordValidator;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("UsersServiceImpl is works...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class) // убирает _

class UsersServiceImplTest {

    private static final String EXIST_USER_EMAIL = "user@gmail.com";


    private static final String NOT_EXIST_USER_EMAIL = "user1@gmail.com";

    private static final String DEFAULT_PASSWORD = "qwerty07";
    private static final User NOT_EXIST_USER = new User(NOT_EXIST_USER_EMAIL, DEFAULT_PASSWORD);
    private static final User EXIST_USER = new User(EXIST_USER_EMAIL, DEFAULT_PASSWORD);



    UsersServiceImpl usersService; // Объект который мы тестируем
    private UsersRepository usersRepository;
    private EmailValidator emailValidator;
    private PasswordValidator passwordValidator;

    @BeforeEach
    public void setUp() {
        // Мы просим Mockito создать объект типа UsersRepository
        usersRepository = Mockito.mock(UsersRepository.class);

        emailValidator = Mockito.mock(EmailValidator.class);
        passwordValidator = Mockito.mock(PasswordValidator.class);

        // явно прописываем поведение методов на конкретных данных
        // stubbing

        // когда у usersRepository вызываем findOneByEmail с аргументом user@gmail.com возвращается пользователь с eмейлом и паролем

        when(usersRepository.findOneByEmail(EXIST_USER_EMAIL)).thenReturn(EXIST_USER);


        // когда у usersRepository вызываем findOneByEmail с аргументом user1@gmail.com возвращается null
        when(usersRepository.findOneByEmail(NOT_EXIST_USER_EMAIL)).thenReturn(null);
// когда EmailValidator вызывает validate на пустом email (это void метод)
        doThrow(IllegalArgumentException.class).when(emailValidator).validate(null);
        doThrow(IllegalArgumentException.class).when(passwordValidator).validate(" ");

        this.usersService = new UsersServiceImpl(usersRepository, emailValidator,passwordValidator);
    }

    @Nested
    @DisplayName("addUser() :")
    class AddUser{

        @Test
        public void on_in_correct_email_throws_exception() {
            // указываем тип ожидаемой ошибки через .class
            assertThrows(IllegalArgumentException.class, () -> usersService.addUser(null, DEFAULT_PASSWORD));
        }

        @Test
        public void on_in_correct_password_throws_exception() {
//            verify(emailValidator).validate("");
            // указываем тип ожидаемой ошибки через .class
            assertThrows(IllegalArgumentException.class, () -> usersService.addUser(EXIST_USER_EMAIL, null));
        }

        @Test
        public void returns_created_user() {
            User actual = usersService.addUser(NOT_EXIST_USER_EMAIL, DEFAULT_PASSWORD);

            // был ли вызван метод save
            verify(usersRepository).save(NOT_EXIST_USER);

            // проверяем был ли вызван метод save
            assertNotNull(actual);
        }

        @Test
        public void on_existed_users_throws_exception() {
            assertThrows(IllegalArgumentException.class, () -> usersService.addUser(EXIST_USER_EMAIL, DEFAULT_PASSWORD));
        }

    }

}