package utility;

import java.util.HashMap;

import classesandenums.User;
import commands.*;

/**
 * Управляет командами.
 */
public class CommandManager {
    private HashMap<String, Command> commands;
    private User user;

    public CommandManager(CollectionManager collectionManager, QuestionAboutPerson questionAboutPerson, User user) {
        commands = new HashMap<>();
        this.user = user;
        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(collectionManager));
        commands.put("show", new ShowCommand(collectionManager));
        commands.put("add", new AddCommand(collectionManager, questionAboutPerson,user));
        commands.put("update", new UpdateCommand(collectionManager, questionAboutPerson,user));
        commands.put("remove_by_id", new RemoveByIdCommand(collectionManager,user));
        commands.put("clear", new ClearCommand(collectionManager,user));
        commands.put("execute_script", new ExecuteScriptCommand(this));
        commands.put("add_if_min", new AddIfMinCommand(collectionManager, questionAboutPerson,user));
        commands.put("remove_greater", new RemoveGreaterCommand(collectionManager, questionAboutPerson,user));
        commands.put("remove_lower", new RemoveLowerCommand(collectionManager, questionAboutPerson,user));
        commands.put("group_counting_by_id", new GroupCountingByIdCommand(collectionManager));
        commands.put("filter_starts_with_name", new FilterStartsWithNameCommand(collectionManager, questionAboutPerson));
        commands.put("print_unique_location", new PrintUniqueLocationCommand(collectionManager, questionAboutPerson));
        commands.put("exit", new ExitCommand());
    }

    public void execute(String commandName, String arguments) {
        try {
            Command command = commands.get(commandName);
            command.execute(arguments);
        } catch (NullPointerException exp) {
            System.out.println("Команда < " + commandName + " > не найдена.");
        }
    }


    public HashMap<String, Command> getCommands() {
        return commands;
    }
}

