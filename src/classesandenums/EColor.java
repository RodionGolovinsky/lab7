package classesandenums;

public enum EColor {
    GREEN,
    BLUE,
    ORANGE,
    WHITE;

    public static String nameList() {
        StringBuilder nameListBuilder = new StringBuilder();
        for (EColor eyeColor : values()) {
            nameListBuilder.append(eyeColor.name()).append(", ");
        }
        String nameList = nameListBuilder.toString();
        return nameList.substring(0, nameList.length() - 2);
    }
}
