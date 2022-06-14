package commands;

import exceptions.CollectionIsEmptyException;
import utility.CollectionManager;
import utility.Console;
import utility.QuestionAboutPerson;

public class PrintUniqueLocationCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final QuestionAboutPerson questionAboutPerson;


    public PrintUniqueLocationCommand(CollectionManager collectionManager, QuestionAboutPerson questionAboutPerson) {
        super("print_unique_location","выводит уникальные значения поля location всех элементов в коллекции");
        this.collectionManager = collectionManager;
        this.questionAboutPerson = questionAboutPerson;
    }

    public boolean execute(String argument) {
       try{
           if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
           if (!argument.isEmpty()) {
               Console.println("Зачем аргумент? Ну да ладно");
           }
            collectionManager.getArrayOfUniqueLocation();
       } catch (CollectionIsEmptyException e) {
           Console.printerror("Коллекция пуста!");
       }
       return true;
    }
}
