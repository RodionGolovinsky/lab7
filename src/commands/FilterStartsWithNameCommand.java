package commands;

import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;
import utility.QuestionAboutPerson;

public class FilterStartsWithNameCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private QuestionAboutPerson questionAboutPerson;

    public FilterStartsWithNameCommand(CollectionManager collectionManager, QuestionAboutPerson questionAboutPerson) {
        super("filter_starts_with_name (name)", "выводит элементы, значение поля name которых начинается с заданной подстроки");
        this.collectionManager = collectionManager;
        this.questionAboutPerson = questionAboutPerson;
    }

    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            collectionManager.filterStartsWithName(argument);
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Необходимо ввести подстроку, с которой должно начинаться имя!");
        }
        return false;

    }
}
