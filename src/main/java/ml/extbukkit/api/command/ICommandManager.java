package ml.extbukkit.api.command;

import ml.extbukkit.api.extension.AExtension;

/**
 * Command manager class
 */
public interface ICommandManager {
    /**
     * Register a command
     *
     * @param extension Extension
     * @param command Command to register
     */
    void registerCommand(AExtension extension, Command command);

    /**
     * Match command by name
     *
     * @param commandName Command name
     * @return Matched command
     */
    Command matchCommand(String commandName);

    /**
     * Get extension, that registered a command
     *
     * @param command Command to get extension from
     * @return Extension, that registered a command
     */
    AExtension getExtensionForCommand(Command command);
}