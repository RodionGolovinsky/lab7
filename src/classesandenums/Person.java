package classesandenums;

import java.time.LocalDateTime;

public class Person implements Comparable<Person> {
    private Long id;
    private String name;
    private Coordinates coordinates;
    private LocalDateTime creationDate;
    private int height;
    private EColor eyeColor;
    private HColor hairColor;
    private Country nationality;
    private Location location;
    private User owner;
    private static double min_height = 0;

    public Person(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, int height,
                  EColor eyeColor, HColor hairColor, Country nationality, Location location, User user) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
        this.owner = user;
    }

    public Person(Long id, String name, Coordinates coordinates, LocalDateTime creationDate, int height,
                  EColor eyeColor, HColor hairColor, Country nationality, Location location) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.height = height;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public int getHeight() {
        return height;
    }

    public EColor getEyeColor() {
        return eyeColor;
    }

    public HColor getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation() {
        return location;
    }

    public int compareTo(Person personObj) {
        return id.compareTo(personObj.getId());

    }

    public static boolean checkValidHeight(int height) {
        if (height < min_height) return false;
        return true;
    }

    public String toString() {
        String info = "";
        info += "Человек " + id;
        info += " (добавлен " + creationDate.toLocalDate() + " " + creationDate.toLocalTime() + ")";
        info += "\n Имя: " + name;
        info += "\n Местоположение: " + coordinates;
        info += "\n Рост: " + height;
        info += "\n Цвет глаз: " + eyeColor;
        info += "\n Цвет волос: " + hairColor;
        info += "\n Страна проживания: " + nationality;
        info += "\n Местороложение: " + location;
        info += "\n Владелец: " + owner.getUsername();
        return info;
    }

    public int hashCode() {
        return name.hashCode() + coordinates.hashCode() + height + eyeColor.hashCode() + eyeColor.hashCode() +
                nationality.hashCode() + location.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Person) {
            Person personObj = (Person) obj;
            return name.equals(personObj.getName()) && coordinates.equals(personObj.getCoordinates()) &&
                    (height == personObj.getHeight()) && (eyeColor == personObj.getEyeColor()) &&
                    (hairColor == personObj.getHairColor()) && (nationality == personObj.getNationality()) &&
                    location.equals(personObj.getLocation()) && (creationDate == personObj.getCreationDate());
        }
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setEyeColor(EColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public void setHairColor(HColor hairColor) {
        this.hairColor = hairColor;
    }

    public void setNationality(Country nationality) {
        this.nationality = nationality;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }
}