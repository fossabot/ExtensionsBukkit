package ml.extbukkit.api.command;

import ml.extbukkit.api.extension.AExtension;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

/**
 * Represents a simple command
 *
 * @author MrIvanPlays
 */
public abstract class Command {
  private AExtension extension;
  private String name;
  private String description = "";
  private String usage = "";
  private String permission = "";
  private String permissionMessage = "";
  private String[] aliases;

  public Command(AExtension extension, String name) {
    this.extension = extension;
    this.name = name;
  }

  public Command(AExtension extension, String name, String description) {
    this(extension, name);
    this.description = description;
  }

  public Command(AExtension extension, String name, String description, String permission, String... aliases) {
    this(extension, name, description);
    this.permission = permission;
    this.aliases = aliases;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  public String getPermissionMessage() {
    return permissionMessage;
  }

  public void setPermissionMessage(String permissionMessage) {
    this.permissionMessage = permissionMessage;
  }

  public String[] getAliases() {
    return aliases;
  }

  public void setAliases(String[] aliases) {
    this.aliases = aliases;
  }

  public String getUsage() {
    return usage;
  }

  public void setUsage(String usage) {
    this.usage = usage;
  }

  public AExtension getExtension() {
    return extension;
  }

  public boolean checkPermission(CommandSender sender) {
    if ((permissionMessage != null || permissionMessage.length() != 0) && (permission != null)) {
      if (!sender.hasPermission(permission)) {
        sender.sendMessage(permissionMessage);
        return false;
      }
    } else if ((permissionMessage == null || permissionMessage.length() == 0)
        && (permission != null)) {
      if (!sender.hasPermission(permission)) {
        sender.sendMessage(
            ChatColor.RED
                + "I'm sorry but you do not have permission to perform this command. If you believe this in error contact an administrator");
        return false;
      }
    } else if (permissionMessage == null || permissionMessage.length() == 0) {
      return true;
    }
    return true;
  }

  public abstract void execute(CommandSender cs, String[] args);

  // TODO: Add tab completers
}
