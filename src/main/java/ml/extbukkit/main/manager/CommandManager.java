package ml.extbukkit.main.manager;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import ml.extbukkit.api.command.Command;
import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.command.exception.CommandException;
import ml.extbukkit.api.extension.AExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager implements ICommandManager {
    private Map<String, Command> commandMap = new HashMap<>();
    private Multimap<AExtension, Command> commandsByExtension = ArrayListMultimap.create();

    @Override
    public void registerCommand(AExtension extension, Command command) {
        commandMap.put(command.getName().toLowerCase(), command);
        if (command.getAliases() != null) {
            for (String alias : command.getAliases()) {
                commandMap.put(alias.toLowerCase(), command);
            }
        }
        if (!command.getExtension().equals(extension) || command.getExtension() == null) {
            command.setExtension(extension);
        }
        commandsByExtension.put(extension, command);
    }

    @Override
    public Command matchCommand(String commandName) {
        return commandMap.get(commandName);
    }

    @Override
    public void dispatchCommand(ICommandExecutor sender, String commandLine) {
        String[] rawArgs = commandLine.split(" ");
        String commandName = rawArgs[0];
        String[] args = Arrays.copyOfRange(rawArgs, 1, rawArgs.length);
        Command executed = matchCommand(commandName);
        if (executed == null) {
            sender.sendMessage("§cThis command cannot be found"); // TODO: Configurable
            return;
        }
        try {
            executed.execute(sender, commandName, args);
        } catch (Throwable t) {
            throw new CommandException("Internal exception executing command '/" + commandLine + "' in extension " + executed.getExtension().getName(), t);
        }
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public Multimap<AExtension, Command> getCommandsByExtension() {
        return commandsByExtension;
    }
}