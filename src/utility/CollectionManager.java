package utility;

import DatabaseWorkers.DataBaseCollectionManager;
import classesandenums.Location;
import classesandenums.Person;
import classesandenums.User;
import exceptions.DatabaseHandlingException;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

public class CollectionManager {
    private static LinkedHashSet<Person> personCollection = new LinkedHashSet<>();
    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final DataBaseCollectionManager dataBaseCollectionManager;

    public CollectionManager(DataBaseCollectionManager dataBaseCollectionManager) {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dataBaseCollectionManager = dataBaseCollectionManager;
        loadCollection();
    }

    public Person[] collectionToArray() {
        Object[] arrayObjectPeople = personCollection.toArray();
        Person[] arrayPeople = new Person[arrayObjectPeople.length];
        for (int i = 0; i < arrayPeople.length; i++) {
            arrayPeople[i] = (Person) arrayObjectPeople[i];
        }
        return arrayPeople;
    }

    /**
     * выводит уникальные значения поля location всех элементов в коллекции
     */
    public void getArrayOfUniqueLocation() {
        LinkedList<Location> Locations = new LinkedList<>();
        personCollection.stream().forEach((P) -> {
            if (!Locations.contains(P.getLocation())) {
                Locations.add(P.getLocation());
            }
        });
        Locations.stream().forEach(System.out::println);
    }

    /**
     * выводит элементы, значение поля name которых начинается с заданной подстроки
     */
    public void filterStartsWithName(String substring) {
        personCollection.stream().filter((person) -> person.getName().substring(0, substring.length() + 1).equals(substring)).forEach(System.out::println);
    }

    /**
     * сгруппировывает элементы коллекции по значению ID поля
     */
    public void groupCountingById() {
        long count = personCollection.stream().filter((person) -> person.getId() % 2 == 0).count();
        System.out.println("Количество четных ID: " + count + ". " + "Количество нечетных ID: " + (personCollection.size() - count) + ".");
    }


    /**
     * возращает время последней инициализации или null, если инициализации не было.
     */
    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }

    /**
     * возращает последнее сохранение времени или ноль, если не было сохранения.
     */
    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }

    /**
     * возращает название типа коллекции.
     */
    public String collectionType() {
        return personCollection.getClass().getName();
    }

    /**
     * возращает размер коллекции.
     */
    public int collectionSize() {
        return personCollection.size();
    }

    /**
     * возращает первый элемент коллекции или null, если коллекция пуста.
     */
    public Person getFirst() {
        if (personCollection.isEmpty()) return null;
        Person[] people = collectionToArray();
        return people[0];
    }

    /**
     * параметр id персона.
     * возвращает персона по его идентификатору или null, если персон не найден.
     */
    public Person getById(Long id) {
        for (Person person : personCollection) {
            if (person.getId().equals(id)) return person;
        }
        return null;
    }

    /**
     * personToFind ищет уникального персона.
     */
    public Person getByValue(Person personToFind) {
        for (Person person : personCollection) {
            if (person.equals(personToFind)) return person;
        }
        return null;
    }

    /**
     * Добавляет нового персона в коллекцию.
     * Добавяет параметр персона.
     */
    public void addToCollection(Person person, User user) {
        try {
            personCollection.add(dataBaseCollectionManager.insertPERSONS(person, user));
            lastSaveTime = LocalDateTime.now();
        } catch (DatabaseHandlingException e) {
            System.out.println("Произошла ошибка при добавлении элемента в БД.");
        }
    }

    /**
     * Удаляет персона из коллекции.
     * Удаляет параметр персона.
     */
    public void removeFromCollection(Person person) {
        personCollection.remove(person);
    }

    /**
     * Удаляет персонов меньше, чем заданный.
     * Параметр personToCompare персона для сравнения.
     */
    public void removeLower(Person personToCompare) {
        for (Person p : personCollection) {
            if (p.compareTo(personToCompare) < 0 && p.getOwner().getUsername().equals(personToCompare.getOwner().getUsername())) {
                try {
                    dataBaseCollectionManager.deletePERSONSById(p.getId());
                    removeFromCollection(p);
                } catch (DatabaseHandlingException ignored) {
                }
            }
        }
        personCollection.removeIf(person -> person.compareTo(personToCompare) < 0);
    }

    /**
     * Удаляет персонов больше, чем заданный.
     * Параметр personToCompare персона для сравнения.
     */
    public void removeGreater(Person personToCompare) {
        personCollection.stream().filter((p) -> p.compareTo(personToCompare) > 0 && p.getOwner().getUsername().equals(personToCompare.getOwner().getUsername())).forEach((p) -> {
            try {
                dataBaseCollectionManager.deletePERSONSById(p.getId());
                removeFromCollection(p);
            } catch (DatabaseHandlingException ignored) {
                //pass
            }
        });
    }

    /**
     * Очищает коллекцию.
     */
    public void clearCollection(User user) {
        personCollection.stream().filter((p) -> p.getOwner().getUsername().equals(user.getUsername())).forEach((p) -> {
            try {
                dataBaseCollectionManager.deletePERSONSById(p.getId());
                removeFromCollection(p);
                lastSaveTime = LocalDateTime.now();
            } catch (DatabaseHandlingException e) {
                System.out.println("Произошла ошибка при удалении элемента из БД.");
            }
        });
    }

    /**
     * Генерирует следующий идентификатор.
     * Возращает Next ID.
     */

    public Long generateNextId() {
        if (personCollection.isEmpty()) return 1L;
        int a = (int) (Math.random() * 400000 + 100000);
        return (Long) (long) a;
    }

    /**
     * Загружает коллекцию из файла.
     */
    private void loadCollection() {
        personCollection = dataBaseCollectionManager.getCollection();
        lastInitTime = LocalDateTime.now();
    }


    public String toString() {
        if (personCollection.isEmpty()) return "Коллекция пуста!";
        Person[] people = collectionToArray();
        String info = "";
        for (Person person : personCollection) {
            info += person;
            if (person != people[people.length - 1]) info += "\n\n";
        }
        return info;
    }

    public void updatePerson(long id, Person person) {
        try {
            dataBaseCollectionManager.updatePERSONSById(id, person);
            removeFromCollection(person);
            personCollection.add(person);
            lastSaveTime = LocalDateTime.now();
        } catch (DatabaseHandlingException e) {
            System.out.println("Произошла ошибка при обновлении элемента.");
        }
    }
}
