package commands;


public class ExitCommand extends AbstractCommand {
    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }


    public boolean execute(String argument) {
        System.exit(0);
        return true;

    }
}
