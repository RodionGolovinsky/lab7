package commands;


import utility.CollectionManager;


public class GroupCountingByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;

    public GroupCountingByIdCommand(CollectionManager collectionManager) {
        super("group_counting_by_id", "сгруппировать элементы коллекции по значению ID поля");
        this.collectionManager = collectionManager;
    }

    public boolean execute(String argument) {
        collectionManager.groupCountingById();
        return true;

    }
}
