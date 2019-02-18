package ml.extbukkit.api.command;

import java.util.List;

/**
 * Tab completer class
 */
public interface TabCompleter {
    /**
     * Handle tab complete
     *
     * @param executor The entity, that executed the command
     * @param arguments Arguments used while tab completing
     * @return List of tab completion variants
     */
    List<String> onTabComplete(ICommandExecutor executor, String[] arguments);
}