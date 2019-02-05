package ml.extbukkit.main.defcommands;

import ml.extbukkit.main.BukkitExtensionsBukkit;
import ml.extbukkit.main.BukkitRunner;
import ml.extbukkit.main.server.Server;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtensionsCommand implements TabExecutor {
  private BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();
  private List<String> confirmationList = new ArrayList<>();

  @Override
  public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
    if (command.getName().equalsIgnoreCase("extensions")) {
      if (args.length == 0) {
        if (sender.hasPermission("extensions.basic")) {
          sender.sendMessage("Commands: /extensions reload ; /extensions list");
          return true;
        }
      }
      if ((args.length == 1) && (args[0].equalsIgnoreCase("list"))) {
        if (sender.hasPermission("extensions.list")) {
          sender.sendMessage("Listing all extensions...");
          Server.getInstance()
              .getExtensionLoader()
              .getExtensions()
              .forEach(
                  extension ->
                      sender.sendMessage(
                          extension.getFullName()
                              + " by "
                              + Arrays.toString(extension.getAuthors())));
          return true;
        }
      }
      if ((args.length == 1)
          && (args[0].equalsIgnoreCase("reload"))
          && (sender.hasPermission("extensions.reload"))) {
        if (confirmationList.contains(sender.getName())) {
          sender.sendMessage(ChatColor.RED + "You already ran that!");
          return true;
        }
        sender.sendMessage(
            ChatColor.RED
                + "Please note: this can cause memory leaks from the extensions and couple of issues");
        sender.sendMessage(
            ChatColor.RED
                + "It is better to restart the server instead of reloading the extensions.");
        sender.sendMessage(
            ChatColor.YELLOW + "Please type '/extensions reload confirm' to confirm the reload!");
        confirmationList.add(sender.getName());
        return true;
      }
      if ((args.length == 2)
          && (args[0].equalsIgnoreCase("reload"))
          && (args[1].equalsIgnoreCase("confirm") && (sender.hasPermission("extensions.reload")))) {
        if (!confirmationList.contains(sender.getName())) {
          sender.sendMessage(
              ChatColor.RED + "You haven't run /extensions reload to do this command!");
          return true;
        }
        sender.sendMessage(
            ChatColor.RED
                + "Please note: this can cause memory leaks from the extensions and couple of issues");
        sender.sendMessage(
            ChatColor.RED
                + "It is better to restart the server instead of reloading the extensions.");
        Server.getInstance().getExtensionLoader().loadAll(Server.getInstance().getExtensionsDir());
        Bukkit.getScheduler().cancelTasks(plugin);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunner(), 0L, 1L);
        plugin.unloadExtensions();
        plugin.loadExtensions();
        plugin.registerCommands();
        confirmationList.remove(sender.getName());
        sender.sendMessage(ChatColor.YELLOW + "Reload complete.");
        return true;
      }
    }
    return true;
  }

  @Override
  public List<String> onTabComplete(
      CommandSender sender, Command command, String s, String[] args) {
    if (command.getName().equalsIgnoreCase("extensions")) {
      if (args.length == 1) {
        List<String> tabCompletion = new ArrayList<>();
        if (sender.hasPermission("extensions.reload")) {
          tabCompletion.add("reload");
        }
        if (sender.hasPermission("extensions.list")) {
          tabCompletion.add("list");
        }
        return tabCompletion.stream()
            .filter(a -> a.startsWith(args[0]))
            .collect(Collectors.toList());
      }
      if (args.length == 2) {
        if (sender.hasPermission("extensions.reload")) {
          return Stream.of("confirm")
              .filter(a -> a.startsWith(args[1]))
              .collect(Collectors.toList());
        }
      }
    }
    return null;
  }
}
