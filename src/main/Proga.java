package main;

import DatabaseWorkers.*;
import utility.CollectionManager;
import utility.CommandManager;
import utility.QuestionAboutPerson;
import java.util.Scanner;

public class Proga {
    public static final String PS2 = "> ";

    public static void main(String[] args) {

        try {
            PropHelper.getProperties();
            DataBaseHandler dataBaseHandler = new DataBaseHandler(PropHelper.getHost(), Integer.parseInt(PropHelper.getPort()), PropHelper.getUser(), PropHelper.getPassword(), PropHelper.getBasename());
            Scanner userScanner = new Scanner(System.in);
            DataBaseUserManager dataBaseUserManager = new DataBaseUserManager(dataBaseHandler);
            AuthorizationHelper helper = new AuthorizationHelper(userScanner, dataBaseUserManager);
            DataBaseCollectionManager dataBaseCollectionManager = new DataBaseCollectionManager(dataBaseHandler,dataBaseUserManager);
            CollectionManager collectionManager = new CollectionManager(dataBaseCollectionManager);
            QuestionAboutPerson questionAboutPerson = new QuestionAboutPerson(userScanner);
            CommandManager commandManager = new CommandManager(collectionManager, questionAboutPerson, helper.run());

            while (true) {
                System.out.println("Введите команду");
                String arguments;
                String[] commandNameAndArguments = userScanner.nextLine().split(" ");
                String commandName = commandNameAndArguments[0];
                if (commandNameAndArguments.length > 1) {
                    arguments = commandNameAndArguments[1];
                } else arguments = "";
                commandManager.execute(commandName, arguments);
            }
        }catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("Произошла катастрофа.");
        }
    }
}
