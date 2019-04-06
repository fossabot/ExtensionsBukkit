package ml.extbukkit.main.secure.command;

import java.util.Arrays;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.chat.ChatMessage;
import ml.extbukkit.api.chat.ChatMessageSerializer;
import ml.extbukkit.api.command.CommandExecutor;
import ml.extbukkit.api.server.Server;
import net.md_5.bungee.chat.ComponentSerializer;
import org.bukkit.Bukkit;

public class Console implements CommandExecutor {

  private Server server = Server.getInstance();

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
  public void sendMessage(ChatMessage message) {
    Bukkit.getConsoleSender().spigot().sendMessage(ComponentSerializer.parse(ChatMessageSerializer.getInstance().toString(message)));
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