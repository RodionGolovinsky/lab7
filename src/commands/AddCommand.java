package commands;

import classesandenums.Person;
import classesandenums.User;
import exceptions.IncorrectInputInScriptException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import utility.QuestionAboutPerson;

import java.time.LocalDateTime;

public class AddCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final QuestionAboutPerson questionAboutPerson;
    private final User user;

    public AddCommand(CollectionManager collectionManager, QuestionAboutPerson questionAboutPerson, User user) {
        super("add ", "добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.questionAboutPerson = questionAboutPerson;
        this.user = user;
    }

    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.addToCollection(new Person(
                    collectionManager.generateNextId(),
                    questionAboutPerson.askName(),
                    questionAboutPerson.askCoordinates(),
                    LocalDateTime.now(),
                    questionAboutPerson.askHeight(),
                    questionAboutPerson.askEyeColour(),
                    questionAboutPerson.askHairColour(),
                    questionAboutPerson.askNationality(),
                    questionAboutPerson.askLocation()
            ), user);
            Console.println("Человек успешно добавлен!");
            return true;
        } catch (WrongAmountOfElementsException exception) { //неправильное количество элементов
            Console.println("Команда не использует аргументы!");
        } catch (IncorrectInputInScriptException e) {
            Console.printerror("Возникла ошибка при сборе данных");
        }
        return false;
    }
}
