package commands;

import classesandenums.*;
import exceptions.*;
import utility.CollectionManager;
import utility.Console;
import utility.QuestionAboutPerson;
import java.time.LocalDateTime;

public class UpdateCommand extends AbstractCommand {
    private CollectionManager collectionManager;
    private QuestionAboutPerson questionAboutPerson;
    private User user;

    public UpdateCommand(CollectionManager collectionManager, QuestionAboutPerson questionAboutPerson, User user) {
        super("update <ID> {element}", "обновить значение элемента коллекции по ID");
        this.collectionManager = collectionManager;
        this.questionAboutPerson = questionAboutPerson;
        this.user=user;
    }


    public boolean execute(String argument) {
        try {
            if (argument.isEmpty()) throw new WrongAmountOfElementsException();
            if (collectionManager.collectionSize() == 0) throw new CollectionIsEmptyException();
            Long id = Long.valueOf(argument);
            Person oldPerson = collectionManager.getById(id);
            if (oldPerson == null) throw new PersonNotFoundException();
            if (!oldPerson.getOwner().getUsername().equals(user.getUsername())) throw new UserAccessException();
            String name = oldPerson.getName();
            Coordinates coordinates = oldPerson.getCoordinates();
            LocalDateTime creationDate = oldPerson.getCreationDate();
            int height = oldPerson.getHeight();
            EColor eyeColor = oldPerson.getEyeColor();
            HColor hairColor = oldPerson.getHairColor();
            Country nationality = oldPerson.getNationality();
            Location location = oldPerson.getLocation();
            if (questionAboutPerson.askQuestion("Хотите изменить имя человека?")) name = questionAboutPerson.askName();
            if (questionAboutPerson.askQuestion("Хотите изменить координаты человека?"))
                coordinates = questionAboutPerson.askCoordinates();
            if (questionAboutPerson.askQuestion("Хотите изменить рост человека?"))
                height = questionAboutPerson.askHeight();
            if (questionAboutPerson.askQuestion("Хотите изменить цвет глаз человека?"))
                eyeColor = questionAboutPerson.askEyeColour();
            if (questionAboutPerson.askQuestion("Хотите изменить цвет волос человека?"))
                hairColor = questionAboutPerson.askHairColour();
            if (questionAboutPerson.askQuestion("Хотите изменить местопроживание человека?"))
                nationality = questionAboutPerson.askNationality();
            if (questionAboutPerson.askQuestion("Хотите изменить местоположение человека?"))
                location = questionAboutPerson.askLocation();
            Person newPerson = new Person(
                    id,
                    name,
                    coordinates,
                    creationDate,
                    height,
                    eyeColor,
                    hairColor,
                    nationality,
                    location,
                    user);
            collectionManager.updatePerson(id, newPerson);
            Console.println("Человек успешно изменен!");
            return true;
        } catch (CollectionIsEmptyException exception) {
            Console.printerror("Коллекция пуста!");
        } catch (NumberFormatException exception) {
            Console.printerror("ID должен быть представлен числом!");
        } catch (PersonNotFoundException exception) {
            Console.printerror("Человека с таким ID в коллекции нет!");
        } catch (WrongAmountOfElementsException e) {
            Console.printerror("Необходимо ввести аргумент");
        } catch (IncorrectInputInScriptException e) {
            Console.printerror("Возникла ошибка при сборе данных");
        } catch (UserAccessException e){
            System.out.println("Вы не можете изменить этого человека!");
        }
        return false;
    }
}
