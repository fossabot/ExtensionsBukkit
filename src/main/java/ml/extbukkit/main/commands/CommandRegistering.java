package ml.extbukkit.main.commands;

import ml.extbukkit.api.command.ExtensionCommandException;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class CommandRegistering extends Command {
  private ml.extbukkit.api.command.Command ourCommand;

  public CommandRegistering(ml.extbukkit.api.command.Command ourCommand) {
    super(
        ourCommand.getName(),
        ourCommand.getDescription(),
        ourCommand.getUsage(),
        Arrays.asList(ourCommand.getAliases() == null ? new String[0] : ourCommand.getAliases()));
    this.ourCommand = ourCommand;
    setPermission(ourCommand.getPermission());
    setPermissionMessage(ourCommand.getPermissionMessage());
  }

  @Override
  public boolean execute(CommandSender sender, String s, String[] args) {
    if (ourCommand.checkPermission(sender)) {
      try {
        ourCommand.execute(sender, args);
      } catch (Throwable exception) {
        sender.sendMessage(
            ChatColor.RED
                + "An internal error occured while trying to execute the specified command");
        throw new ExtensionCommandException(
            "Unhandled exception executing command '"
                + ourCommand.getName()
                + "' in extension "
                + ourCommand.getExtension().getFullName(),
            exception);
      }
    }
    return true;
  }
}
