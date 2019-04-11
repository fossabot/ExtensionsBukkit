package ml.extbukkit.api.command;

import ml.extbukkit.api.extension.Extension;

/**
 * Command manager class
 */
public interface CommandManager {
    /**
     * Register a command
     *
     * @param extension Extension
     * @param command   Command to register
     */
    void registerCommand(Extension extension, Command command);

    /**
     * Match command by name
     *
     * @param commandName Command name
     * @return Matched command
     */
    Command matchCommand(String commandName);
}