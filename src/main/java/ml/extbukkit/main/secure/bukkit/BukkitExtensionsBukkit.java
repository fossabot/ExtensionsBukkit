package ml.extbukkit.main.secure.bukkit;

import java.io.File;

import ml.extbukkit.api.chat.ChatMessageSerializer;
import ml.extbukkit.api.loader.ExtensionLoader;
import ml.extbukkit.api.server.Server;
import ml.extbukkit.main.secure.chat.SimpleSerializer;
import ml.extbukkit.main.secure.command.SimpleCommandManager;
import ml.extbukkit.main.secure.cooldowns.EventListener;
import ml.extbukkit.main.secure.log.SimpleLogger;
import ml.extbukkit.main.secure.log.util.LevelToChannel;
import ml.extbukkit.main.secure.nms.reflection.NMSRUtil;
import ml.extbukkit.main.secure.server.ExtensionedServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.bukkit.plugin.java.JavaPlugin;

public final class BukkitExtensionsBukkit extends JavaPlugin {
  private static BukkitExtensionsBukkit I;
  private SimpleLogger extensionsLogger = SimpleLogger.getInstance();
  private ExtensionedServer server = new ExtensionedServer();

  public BukkitExtensionsBukkit() {
    I = this;
  }

  public static BukkitExtensionsBukkit getInstance() {
    return I;
  }

  @Override
  public void onLoad() {
    if(NMSRUtil.isUnder1_12()) {
      getLogger().severe("Unsupported version found! Minimal supported version: 1.12");
      getLogger().severe("Plugin disabled!");
      getServer().getPluginManager().disablePlugin(this);
      return;
    }
    ChatMessageSerializer.setInstance(new SimpleSerializer());
    Server.setInstance(server);
    ((Logger) LogManager.getRootLogger()).addFilter(new AbstractFilter() {
      @Override
      public Result filter(LogEvent event) {
        if(event.getMessage().getFormattedMessage().contains("ExtensionsBukkit")) {
          extensionsLogger.log(LevelToChannel.transform(event.getLevel()), event.getMessage().getFormattedMessage());
          if(event.getThrown() != null) {
            extensionsLogger.logStack(event.getThrown().getMessage(), event.getThrown());
          }
        } else {
          extensionsLogger.logBukkit(event.getLevel(), event.getMessage().getFormattedMessage());
          if(event.getThrown() != null) {
            extensionsLogger.logBukkitStack(event.getThrown());
          }
        }
        return Result.DENY;
      }
    });
    if(!server.getExtensionsDir().exists()) {
      server.getExtensionsDir().mkdirs();
    }
    server.getExtensionLoader().loadAll(server.getExtensionsDir());
    SimpleCommandManager.getInstance().registerCommands();
    if(getFile().exists()) {
      getFile().delete();
    }
    Updater.download();
  }

  @Override
  public void onEnable() {
    if(!getServer().getPluginManager().isPluginEnabled(this)) {
      return;
    }
    new EventListener();
    getServer().getPluginManager().registerEvents(new BukkitEventListener(), this);
    getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunner(), 0L, 1L);
  }

  @Override
  public void onDisable() {
    getServer().getScheduler().cancelTasks(this);
    ExtensionLoader extensionLoader = server.getExtensionLoader();
    extensionLoader.getExtensions().forEach(extensionLoader::disable);
    extensionsLogger.closeLog();
  }

  @Override
  public File getFile() {
    return super.getFile();
  }

  public ExtensionedServer getServerImplementation() {
    return server;
  }
}
