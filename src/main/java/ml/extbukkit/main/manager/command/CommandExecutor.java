package ml.extbukkit.main.manager.command;

import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.util.Wrapper;
import org.bukkit.command.CommandSender;

public class CommandExecutor extends Wrapper<CommandSender> implements ICommandExecutor {
    public CommandExecutor(CommandSender handle) {
        super(handle);
    }

    @Override
    public void sendMessage(String message) {
        handle.sendMessage(message);
    }

    @Override
    public void sendMessages(String... message) {
        handle.sendMessage(message);
    }

    @Override
    public boolean hasPermission(String permission) {
        return handle.hasPermission(permission);
    }

    @Override
    public String getName() {
        return handle.getName();
    }
}