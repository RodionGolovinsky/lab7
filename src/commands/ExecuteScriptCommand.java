package commands;

import utility.CommandManager;
import utility.Console;
import utility.FileReaderForScript;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class ExecuteScriptCommand extends AbstractCommand {
    CommandManager commandManager;

    public ExecuteScriptCommand(CommandManager commandManager) {
        super("execute_script <file_name>", "исполнить скрипт из указанного файла");
        this.commandManager = commandManager;
    }

    public boolean execute(String argument) {
        FileReaderForScript readerForScript = new FileReaderForScript();
        Path path;
        try {
            path = readerForScript.input();
            Scanner inputFromFile = new Scanner(path);
            while (inputFromFile.hasNext()) {
                String arguments;
                String[] commandNameAndArguments = inputFromFile.nextLine().split(" ");
                String commandName = commandNameAndArguments[0];
                if (commandNameAndArguments.length > 1) {
                    arguments = commandNameAndArguments[1];
                } else arguments = "";
                commandManager.execute(commandName, arguments);
                System.out.println("");
            }
        } catch (IOException e) {
            Console.printerror("Похоже у вас недостаточно прав доступа!");
        }
        return true;
    }
}
