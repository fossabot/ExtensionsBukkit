package ml.extbukkit.main.secure.command;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import ml.extbukkit.api.command.Command;
import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.command.exception.CommandException;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.main.secure.bukkit.BukkitExtensionsBukkit;
import ml.extbukkit.main.secure.manager.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CommandManager implements ICommandManager {
    private Map<String, Command> commandMap = new HashMap<>();
    private Multimap<AExtension, Command> commandsByExtension = ArrayListMultimap.create();
    private Map<Command, AExtension> extensionsByComand = new HashMap<>();
    private CommandMap bcmp;
    private boolean r;

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

    public void registerCommands() {
        if(r) return;
        BukkitExtensionsBukkit p = BukkitExtensionsBukkit.getInstance();
        try {
            Field field = p.getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            bcmp = (CommandMap) field.get(p.getServer());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        getCommandsByExtension().asMap().forEach((extension, registeredCommands) -> registeredCommands.forEach(command ->
        {
            String registeredName = p.getName() + ":" + extension.getName();
            bcmp.register(command.getName(), registeredName, new org.bukkit.command.Command(command.getName(), "", "", Collections.emptyList()) {
                @Override
                public boolean execute(CommandSender commandSender, String s, String[] strings) {
//                    ICommandExecutor executor = commandSender instanceof ConsoleCommandSender ? Server.getInstance().getConsole() : new CommandExecutor(commandSender);
                    ICommandExecutor executor = new CommandExecutor( commandSender ); // Time only
                    try {
                        command.execute(executor, s, strings);
                    } catch (Throwable t) {
                        throw new CommandException("Internal exception executing command '/" + s + "' in extension " + extension.getName(), t);
                    }
                    return true;
                }
            });
        }));
        r = true;
    }
}