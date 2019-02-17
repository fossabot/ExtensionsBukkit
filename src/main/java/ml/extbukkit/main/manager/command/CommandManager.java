package ml.extbukkit.main.manager.command;

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
    private Map<Command, AExtension> extensionsByComand = new HashMap<>();

    @Override
    public void registerCommand(AExtension extension, Command command) {
        commandMap.put(command.getName().toLowerCase(), command);
        if (command.getAliases() != null) {
            for (String alias : command.getAliases()) {
                commandMap.put(alias.toLowerCase(), command);
            }
        }
        commandsByExtension.put(extension, command);
        extensionsByComand.put(command, extension);
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
            AExtension extension = getExtensionForCommand(executed);
            throw new CommandException("Internal exception executing command '/" + commandLine + "' in extension '" + extension.getName() + "'", t);
        }
    }

    @Override
    public AExtension getExtensionForCommand(Command command) {
        return extensionsByComand.get(command);
    }

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public Multimap<AExtension, Command> getCommandsByExtension() {
        return commandsByExtension;
    }
}