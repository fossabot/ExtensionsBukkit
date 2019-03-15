package ml.extbukkit.main.secure.command;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ml.extbukkit.api.command.Command;
import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.main.secure.bukkit.BukkitExtensionsBukkit;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.NONE)
public class CommandManager implements ICommandManager {
    private Map<String, Command> commandMap = new HashMap<>();
    private Multimap<AExtension, Command> commandsByExtension = ArrayListMultimap.create();
    private CommandMap bcmp;
    private static CommandManager instance = new CommandManager();

    public static CommandManager getInstance()
    {
        return instance;
    }

    @Override
    public void registerCommand(AExtension extension, Command command) {
        commandMap.put(command.getName().toLowerCase(), command);
        if (command.getAliases() != null) {
            for (String alias : command.getAliases()) {
                commandMap.put(alias.toLowerCase(), command);
            }
        }
        commandsByExtension.put(extension, command);
    }

    @Override
    public Command matchCommand(String commandName) {
        return commandMap.get(commandName);
    }

    /*@Override
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
    }*/

    public Map<String, Command> getCommandMap() {
        return commandMap;
    }

    public void registerCommands() {
        BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            bcmp = (CommandMap) field.get(Bukkit.getServer());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        commandsByExtension.forEach( (extension, command) ->
        {
            String registerName = plugin.getName() + ":" + extension.getName();
            bcmp.register( command.getName(), registerName, new BridgeCommand( command, extension ) );
        });
    }
}