package commands;

import utility.Console;

import java.util.HashMap;

public class HelpCommand extends AbstractCommand {
    private final HashMap<String, Command> commands;

    public HelpCommand(HashMap<String, Command> commands) {
        super("help", "вывести справку по доступным командам");
        this.commands = commands;

    }


    public boolean execute(String argument) {
        for (Command command : commands.values()) {
            Console.printtable(command.getName(), command.getDescription());
        }
        return true;
    }
}
