package DatabaseWorkers;

import utility.Console;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropHelper {

    private static final String HOST_KEY = "db.host";
    private static final String PORT_KEY = "db.port";
    private static final String BASENAME_KEY = "db.basename";
    private static final String USER_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";

    private static String Host;
    private static String Port;
    private static String Basename;
    private static String User;
    private static String Password;

    private static final Properties PROPERTIES = new Properties();

    static {
        loadProperties();
    }

    public static String get(String key) {

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
        Host = PropHelper.get(HOST_KEY);
        Port = PropHelper.get(PORT_KEY);
        Basename = PropHelper.get(BASENAME_KEY);
        User = PropHelper.get(USER_KEY);
        Password = PropHelper.get(PASSWORD_KEY);
        if (Host == null || Port == null || Basename == null || User == null || Password == null)
            throw new RuntimeException("Не удалось прочитать параметры подключения к БД");
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
