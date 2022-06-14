package classesandenums;

public enum HColor {
    GREEN,
    BLACK,
    YELLOW,
    WHITE;

    public static String nameList() {
        StringBuilder nameListBuilder = new StringBuilder();
        for (HColor hairColor : values()) {
            nameListBuilder.append(hairColor.name()).append(", ");
        }
        String nameList = nameListBuilder.toString();
        return nameList.substring(0, nameList.length() - 2);
    }
}
