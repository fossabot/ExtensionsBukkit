package ml.extbukkit.api.command;

import ml.extbukkit.api.extension.AExtension;

public interface ICommandManager {
    void registerCommand(AExtension extension, Command command);
    Command matchCommand(String commandName);
    void dispatchCommand(ICommandExecutor sender, String commandLine);
}