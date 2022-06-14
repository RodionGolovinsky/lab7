package utility;


public class Console {


    public static void print(Object toOut) {
        System.out.print(toOut);
    }


    public static void println(Object toOut) {
        System.out.println(toOut);
    }


    public static void printerror(Object toOut) {
        System.err.println("error: " + toOut);
    }

    public static void printtable(Object element1, Object element2) {
        System.out.printf("%-37s%-1s%n", element1, element2);
    }


    public String toString() {
        return "Console (класс для обработки ввода команд)";
    }
}
