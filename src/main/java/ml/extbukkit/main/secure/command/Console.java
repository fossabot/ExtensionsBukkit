package ml.extbukkit.main.secure.command;

import com.google.gson.JsonArray;
import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.server.IServer;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;

import java.util.Arrays;

public class Console implements ICommandExecutor {

    private IServer server = IServer.getInstance();

    @Override
    public void sendMessage(String message) {
        server.getGlobalLogger().log(Channels.INFO, message);
    }

    @Override
    public void sendMessages(String... message) {
        Arrays.stream(message).forEach(this::sendMessage);
    }

    @Override
    public void executeCommand(String command) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command); // Time only
    }

    @Override
    public void sendMessage(JsonArray message) {
        Bukkit.getConsoleSender().spigot().sendMessage(ComponentSerializer.parse(message.toString()));
    }

    @Override
    public String getName() {
        return "CONSOLE";
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }
}