package ml.extbukkit.main.manager.command;

import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.util.AWrapper;
import org.bukkit.command.ConsoleCommandSender;

public class Console extends AWrapper<ConsoleCommandSender> implements ICommandExecutor {
    public Console(ConsoleCommandSender handle) {
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