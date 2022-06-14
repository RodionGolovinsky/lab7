package DatabaseWorkers;

import classesandenums.User;
import exceptions.DatabaseHandlingException;
import org.apache.commons.codec.digest.DigestUtils;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthorizationHelper {

    private Scanner scanner;
    private DataBaseUserManager dataBaseUserManager;

    public AuthorizationHelper(Scanner scanner, DataBaseUserManager dataBaseUserManager) {
        this.scanner = scanner;
        this.dataBaseUserManager = dataBaseUserManager;
    }

    public User run() {
        User user = null;
        boolean success = false;
        while (!success) {
            try {
                if (askAboutSign()) {
                    System.out.print("Введите логин: ");
                    String login = scanner.nextLine();
                    try {
                        dataBaseUserManager.getUserByUsername(login);
                        System.out.println("Пользователь с таким логином уже существует");
                    } catch (SQLException e) {
                        System.out.print("Введите пароль: ");
                        String rawPassword = scanner.nextLine();
                        String password = DigestUtils.shaHex(rawPassword);//???
                        user = new User(login, password);
                        dataBaseUserManager.insertUser(user);
                        System.out.println("Регистрация прошла успешно!");
                        System.out.println("Авторизация прошла успешно!");
                        success = true;
                    }
                } else {
                    System.out.print("Ввведите логин: ");
                    String login = scanner.nextLine();
                    System.out.print("Введите пароль: ");
                    String rawPassword = scanner.nextLine();
                    String password = DigestUtils.shaHex(rawPassword);
                    user = new User(login, password);
                    if (dataBaseUserManager.checkUserByUsernameAndPassword(user)) {
                        System.out.println("Авторизация прошла успешно!");
                        success = true;
                    } else {
                        System.out.println("Логин или пароль введён неверно");
                    }
                }
            } catch (DatabaseHandlingException exception) {
                System.out.println("Отказано в авторизации");
            }
        }
        return user;
    }

    private boolean askAboutSign() {
        while (true) {
            System.out.println("Если у вас есть аккаунт, введите '1'. Если у вас нет аккаунта, введите '0'): ");
            String answer = scanner.nextLine().trim();
                        if (answer.equals("0")) {
                return true;
            } else if (answer.equals("1")) {
                return false;
            }
            System.out.println("Ввод некорректен!");
        }
    }
}
