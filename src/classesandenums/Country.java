package classesandenums;

 public enum Country {
    RUSSIA,
     GERMANY,
     THAILAND;

    public static String nameList() {
        StringBuilder nameListBuilder = new StringBuilder();
        for (Country nationality : values()) {
            nameListBuilder.append(nationality.name()).append(", ");
        }
        String nameList = nameListBuilder.toString();
        return nameList.substring(0, nameList.length()-2);
    }
 }
