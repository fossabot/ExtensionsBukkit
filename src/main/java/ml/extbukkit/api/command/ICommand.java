package ml.extbukkit.api.command;

import java.util.List;

/**
 * Represents a simple command
 *
 * @author MrIvanPlays, TSEngineer
 */
public interface ICommand {
    void execute(ICommandExecutor executor, String command, List<String> arguments);
    String[] getCommands();
    List<String> complete(ICommandExecutor executor, String command, List<String> arguments);
}