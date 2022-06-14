package classesandenums;

public enum EColor {
    GREEN,
    BLUE,
    ORANGE,
    WHITE;

    public static String nameList() {
        String nameList = "";
        for (EColor eyeColor : values()) {
            nameList += eyeColor.name() + ", ";
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}
