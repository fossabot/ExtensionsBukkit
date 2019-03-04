package ml.extbukkit.api.command;

import java.util.List;

/**
 * Tab completer class
 */
public interface TabCompleter {

    /**
     * Handle tab complete
     *
     * @param sender The entity, that executed the command
     * @param args Arguments used while tab completing
     * @return List of tab completion variants for the specific command
     */
    List<String> onTabComplete(ICommandExecutor sender, String[] args);
}