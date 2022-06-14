package commands;

import classesandenums.Person;
import classesandenums.User;
import exceptions.CollectionIsEmptyException;
import exceptions.PersonNotFoundException;
import exceptions.UserAccessException;
import exceptions.WrongAmountOfElementsException;
import utility.CollectionManager;
import utility.Console;

public class RemoveByIdCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private User user;

    public RemoveByIdCommand(CollectionManager collectionManager, User user) {
        super("remove_by_id <ID>", "удалить элемент из коллекции по ID");
        this.collectionManager = collectionManager;
        this.user = user;
    }

    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = Long.parseLong(argument);
            Person personToRemove = collectionManager.getById(id);
            if (personToRemove == null) throw new PersonNotFoundException();
            if (!personToRemove.getOwner().getUsername().equals(user.getUsername())) throw new UserAccessException();
            collectionManager.removeFromCollection(personToRemove);
            Console.println("Человек успешно удален!");
            return true;
        } catch (WrongAmountOfElementsException exception) {
            Console.println("Использование: '" + getName() + "'");
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (PersonNotFoundException exception) {
            Console.printerror("Человека с таким ID в коллекции нет!");
        } catch (UserAccessException exception){
            System.out.println("У вас недостаточно прав, чтобы удалить человека с этим ID!");
        }
        return false;
    }
}
