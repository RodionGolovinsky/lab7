package commands;

import classesandenums.User;
import utility.CollectionManager;
import utility.Console;

public class ClearCommand extends AbstractCommand {
    private final CollectionManager collectionManager;
    private final User user;

    public ClearCommand(CollectionManager collectionManager, User user) {
        super("clear", "очистить коллекцию");
        this.collectionManager = collectionManager;
        this.user = user;
    }

    public boolean execute(String argument) {
        collectionManager.clearCollection(user);
        Console.println("Коллекция очищена!");
        return true;

    }
}
