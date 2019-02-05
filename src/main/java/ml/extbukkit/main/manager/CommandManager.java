package ml.extbukkit.main.manager;

import ml.extbukkit.api.command.ICommand;
import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.extension.AExtension;

import java.util.ArrayList;
import java.util.List;

public class CommandManager implements ICommandManager {
    private List<ICommand> commands = new ArrayList<>();

    @Override
    public void register(AExtension extension, ICommand command) {
        commands.add(command);
    }
    @Override
    public List<ICommand> getCommands() {
        return commands;
    }
    @Override
    public ICommand match(String command) {
        for(ICommand cmd : commands)
            for(String s : cmd.getCommands())
                if(s.equals(command))
                    return cmd;
        return null;
    }
}