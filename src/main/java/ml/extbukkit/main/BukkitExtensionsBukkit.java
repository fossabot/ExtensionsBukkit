package ml.extbukkit.main;

import ml.extbukkit.api.builtin.events.EventLoad;
import ml.extbukkit.main.server.Server;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

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
      if(!Bukkit.getVersion().contains("1.13")) {
          getLogger().severe("UNSUPPORTED MINECRAFT VERSION! STOPPING SERVER");
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
      /*((Logger) LogManager.getRootLogger()).addFilter(new AbstractFilter() {
          @Override
          public Result filter(LogEvent event) {
              return Result.DENY;
          }
      });*/
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
