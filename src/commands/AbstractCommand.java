package commands;

public abstract class AbstractCommand implements Command { //AbstractCommand реализовывает интерфейс Command
    private final String name;
    private final String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String toString() {
        return name + " (" + description + ")";
    }

    public int hashCode() {
        return name.hashCode() + description.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false; //определяет если два объекта не равны то false
        AbstractCommand other = (AbstractCommand) obj;
        return name.equals(other.name) && description.equals(other.description);
    }
}

