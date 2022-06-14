package DatabaseWorkers;

import classesandenums.User;
import exceptions.DatabaseHandlingException;
import org.apache.commons.codec.digest.DigestUtils;
import utility.Console;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthorizationHelper {

    private final Scanner scanner;
    private final DataBaseUserManager dataBaseUserManager;

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
                        Console.printerror("Пользователь с таким логином уже существует");
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
                        Console.printerror("Логин или пароль введён неверно");
                    }
                }
            } catch (DatabaseHandlingException exception) {
                Console.printerror("Отказано в авторизации");
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
