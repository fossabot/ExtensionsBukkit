package ml.extbukkit.main.secure.command;

import ml.extbukkit.api.chat.ChatMessage;
import ml.extbukkit.api.chat.ChatMessageSerializer;
import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.util.AWrapper;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class CommandExecutor extends AWrapper<CommandSender> implements ICommandExecutor {

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
    public void executeCommand(String command) {
        Bukkit.dispatchCommand(handle, command);
    }

    @Override
    public void sendMessage(ChatMessage message) {
        handle.spigot().sendMessage( ComponentSerializer.parse( ChatMessageSerializer.getInstance().toString( message ) ) );
    }

    @Override
    public boolean hasPermission(String permission) {
        return handle.hasPermission(permission);
    }
}