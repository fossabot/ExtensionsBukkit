package ml.extbukkit.main.secure.command;

import ml.extbukkit.api.chat.ChatMessage;
import ml.extbukkit.api.chat.ChatMessageSerializer;
import ml.extbukkit.api.util.Wrapper;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class ExtensionedCommandExecutor extends Wrapper<CommandSender> implements ml.extbukkit.api.command.CommandExecutor {

    public ExtensionedCommandExecutor(CommandSender handle) {
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
        handle.spigot().sendMessage(ComponentSerializer.parse(ChatMessageSerializer.getInstance().toString(message)));
    }

    @Override
    public String getName() {
        return handle.getName();
    }

    @Override
    public boolean hasPermission(String permission) {
        return handle.hasPermission(permission);
    }
}