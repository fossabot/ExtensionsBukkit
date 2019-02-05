package ml.extbukkit.main;

import ml.extbukkit.api.builtin.events.EventLoad;
import ml.extbukkit.main.commands.CommandRegistering;
import ml.extbukkit.main.defcommands.ExtensionsCommand;
import ml.extbukkit.main.server.Server;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;

public final class BukkitExtensionsBukkit extends JavaPlugin {
  private static BukkitExtensionsBukkit I;
  private CommandMap commandMap;

  public BukkitExtensionsBukkit() {
    I = this;
  }

  public static BukkitExtensionsBukkit getInstance() {
    return I;
  }

  @Override
  public void onLoad() {
    try {
      Field cmdMapField = getServer().getClass().getDeclaredField("commandMap");
      cmdMapField.setAccessible(true);
      commandMap = (CommandMap) cmdMapField.get(getServer());
    } catch (IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    }
    if (!Server.getInstance().getExtensionsDir().exists())
      Server.getInstance().getExtensionsDir().mkdirs();
    Server.getInstance().getEventManager().pullEvent(new EventLoad());
    if (getFile().exists()) getFile().delete();
    Updater.download();
  }

  @Override
  public void onEnable() {
    loadExtensions();
    registerCommands();
    ExtensionsCommand pluginCommand = new ExtensionsCommand();
    getCommand("extensions").setExecutor(pluginCommand);
    getCommand("extensions").setTabCompleter(pluginCommand);
    getCommand("extensions").setPermissionMessage(ChatColor.RED + "You don't have permission to perform this command!");
    Bukkit.getPluginManager().registerEvents(new BukkitEventListener(), this);
    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunner(), 0L, 1L);
  }

  @Override
  public void onDisable() {
    unloadExtensions();
    Bukkit.getScheduler().cancelTasks(this);
  }

  @Override
  public File getFile() {
    return super.getFile();
  }

  public void registerCommands() {
    Server.getInstance().getRegisteredCommands().forEach(command -> {
      String extensionName = this.getName() + "-" + command.getExtension().getName();
      commandMap.register(command.getName(), extensionName, new CommandRegistering(command));
    });
  }

  public void loadExtensions() {
    Server.getInstance().getExtensionLoader().loadAll(Server.getInstance().getExtensionsDir());
    Server.getInstance()
        .getExtensionLoader()
        .getExtensions()
        .forEach(
            extension -> {
              extension.getLogger().info("Enabling " + extension.getFullName());
              extension.onEnable();
            });
  }

  public void unloadExtensions() {
    Server.getInstance()
        .getExtensionLoader()
        .getExtensions()
        .forEach(
            extension -> {
              extension.getLogger().info("Disabling " + extension.getFullName());
              extension.onDisable();
            });
  }
}
