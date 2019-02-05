package ml.extbukkit.main;

import ml.extbukkit.api.builtin.events.EventLoad;
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
    ExtensionsCommand pluginCommand = new ExtensionsCommand();
    getCommand("extensions").setExecutor(pluginCommand);
    getCommand("extensions").setTabCompleter(pluginCommand);
    getCommand("extensions").setPermissionMessage(ChatColor.RED + "You don't have permission to perform this command!");
    Bukkit.getPluginManager().registerEvents(new BukkitEventListener(), this);
    Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunner(), 0L, 1L);
  }

  @Override
  public void onDisable() {
    Bukkit.getScheduler().cancelTasks(this);
  }

  @Override
  public File getFile() {
    return super.getFile();
  }
}
