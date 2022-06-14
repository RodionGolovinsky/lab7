package classesandenums;

 public enum Country {
    RUSSIA,
     GERMANY,
     THAILAND;

    public static String nameList() {
        String nameList = "";
        for (Country nationality : values()) {
            nameList += nationality.name() + ", ";
        }
        return nameList.substring(0, nameList.length()-2);
    }
 }
