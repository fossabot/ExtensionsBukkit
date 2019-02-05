package ml.extbukkit.api.command;

import ml.extbukkit.api.extension.AExtension;

import java.util.List;

public interface ICommandManager {
    void register(AExtension extension, ICommand command);
    List<ICommand> getCommands();
    ICommand match(String command);
}