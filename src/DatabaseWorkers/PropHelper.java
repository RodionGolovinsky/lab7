package DatabaseWorkers;

import utility.Console;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Scanner;

public class PropHelper {

    private static String HOST_KEY = "db.host";
    private static String PORT_KEY = "db.port";
    private static String BASENAME_KEY = "db.basename";
    private static String USER_KEY = "db.user";
    private static String PASSWORD_KEY = "db.password";

    private static String Host;
    private static String Port;
    private static String Basename;
    private static String User;
    private static String Password;

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }
    private static void loadProperties() {
        InputStream stream = PropHelper.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            Console.printerror("Ошибка получения параметров базы данных!");
            throw new RuntimeException();
        }

    }


    public static void getProperties() throws RuntimeException {
//        String OS = System.getProperty("os.name").toLowerCase();
//         Scanner scan = null;
//        if (OS.contains("win")) {
//            scan = scan(System.getProperty("user.dir") + "\\" + "database.properties");
//        } else {
//            scan = scan(System.getProperty("user.dir") + "/" + "database.properties");
//        }
//        fillFields(scan);
        Host = PropHelper.get(HOST_KEY);
        Port = PropHelper.get(PORT_KEY);
        Basename = PropHelper.get(BASENAME_KEY);
        User = PropHelper.get(USER_KEY);
        Password = PropHelper.get(PASSWORD_KEY);
        if (Host == null || Port == null || Basename == null || User == null || Password == null)
            throw new RuntimeException("Не удалось прочитать параметры подключения к БД");
    }



    /**
     * вспомогательный метод, разбивает полученный файл на строки
     *
     * @param filepath полный путь к файлу, который нужно прочитать
     * @return возвращает объект типа Scanner с разделением считываемого файла на строки
     */
    private static Scanner scan(String filepath) {
        Path path = Paths.get(filepath);
        Scanner scanner = null;
        while (scanner == null) {
            try {
                scanner = new Scanner(path);
            } catch (IOException e) {
                System.out.println("database.properties file not found");
                return null;
            }
        }
        scanner.useDelimiter(System.getProperty("line.separator"));
        return scanner;
    }

    private static String[] lineParser(String line) {
        try {
            Scanner scanner = new Scanner(line);
            scanner.useDelimiter(":");
            String field = scanner.next();
            String data = "";
            data = scanner.next();
            return new String[]{field, data};
        } catch (NoSuchElementException e) {
            return new String[]{"  ", " "};
        }
    }

    private static void fillFields(Scanner scanner) {
        while (scanner.hasNext()) {
            String[] arguments = lineParser(scanner.next());
            switch (arguments[0]) {
                case ("Host"): {
                    Host = arguments[1];
                    break;
                }
                case ("Port"): {
                    Port = arguments[1];
                    break;
                }
                case ("Basename"): {
                    Basename = arguments[1];
                    break;
                }
                case ("User"): {
                    User = arguments[1];
                    break;
                }
                case ("Password"): {
                    Password = arguments[1];
                    break;
                }
            }
        }
    }

    public static String getHost() {
        return Host;
    }

    public static String getPort() {
        return Port;
    }

    public static String getBasename() {
        return Basename;
    }

    public static String getUser() {
        return User;
    }

    public static String getPassword() {
        return Password;
    }
}
