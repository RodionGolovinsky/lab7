package commands;


import utility.CollectionManager;
import utility.Console;

public class ShowCommand extends AbstractCommand {

    private final CollectionManager collectionManager;

    public ShowCommand(CollectionManager collectionManager) {
        super("show", "вывести все элементы коллекции");
        this.collectionManager = collectionManager;
    }


    public boolean execute(String argument) {
        Console.println(collectionManager);
        return true;
    }
}
