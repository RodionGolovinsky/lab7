package utility;

import java.io.File;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileReaderForScript {
    public Path input() {
        Path pathInput = null;
        while (true) {
            Scanner fileNameInput = new Scanner(System.in);
            System.out.print("Введите путь до файла: ");
            try {
                pathInput = Paths.get(fileNameInput.nextLine());
            } catch (InvalidPathException exception) {
                Console.printerror("Ошибка чтения пути файла");
            }
            assert pathInput != null;
            if (!pathInput.isAbsolute()) {
                Console.printerror("Это не абсолютный путь до файла." + "\n");
                continue;
            }
            File file = new File(String.valueOf(pathInput));
            if (file.isDirectory()) {
                Console.printerror("Вы ввели путь до директории, а не файла." + "\n");
                continue;
            }
            if (!file.exists()) {
                Console.printerror("Такого файла не существует. Проверьте ввод." + "\n");
                continue;
            }
            return pathInput;
        }
    }
}
