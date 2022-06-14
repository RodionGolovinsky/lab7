package classesandenums;

public enum HColor {
    GREEN,
    BLACK,
    YELLOW,
    WHITE;

    public static String nameList() {
        String nameList = "";
        for (HColor hairColor : values()) {
            nameList += hairColor.name() + ", ";
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
