package ml.extbukkit.main;

import ml.extbukkit.api.builtin.events.EventLoad;
import ml.extbukkit.main.server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

public final class BukkitExtensionsBukkit extends JavaPlugin {
  private static BukkitExtensionsBukkit I;

  public BukkitExtensionsBukkit() {
    I = this;
  }

  public static BukkitExtensionsBukkit getInstance() {
    return I;
  }

  @Override
  public void onLoad() {
      /*((Logger) LogManager.getRootLogger()).addFilter(new AbstractFilter() {
          @Override
          public Result filter(LogEvent event) {
              ((ml.extbukkit.main.manager.Logger) Server.getInstance().getLogger()).logBukkit(event.getLevel(), event.getMessage().getFormattedMessage());
              return Result.DENY;
          }
      });*/
      if(!Bukkit.getVersion().contains("1.13")) {
          Server.getInstance().getLogger().log("UNSUPPORTED MINECRAFT VERSION! STOPPING SERVER");
          Bukkit.shutdown();
      }
      if (!Server.getInstance().getExtensionsDir().exists())
          Server.getInstance().getExtensionsDir().mkdirs();
      Server.getInstance().getExtensionLoader().loadAll(Server.getInstance().getExtensionsDir());
      Server.getInstance().getEventManager().pullEvent(new EventLoad());
      if (getFile().exists()) getFile().delete();
      Updater.download();
  }

  @Override
  public void onEnable() {
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
