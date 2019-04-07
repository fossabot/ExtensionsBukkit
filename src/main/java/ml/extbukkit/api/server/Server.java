package ml.extbukkit.api.server;

import com.google.common.base.Preconditions;
import lombok.Getter;
import ml.extbukkit.api.command.CommandExecutor;
import ml.extbukkit.api.command.CommandManager;
import ml.extbukkit.api.config.Configuration;
import ml.extbukkit.api.connection.ExtensionedPlayer;
import ml.extbukkit.api.cooldowns.CooldownManager;
import ml.extbukkit.api.cooldowns.CooldownManagerRegisterer;
import ml.extbukkit.api.event.EventManager;
import ml.extbukkit.api.extension.Extension;
import ml.extbukkit.api.loader.ExtensionLoader;
import ml.extbukkit.api.log.ExtensionLogger;
import ml.extbukkit.api.log.Logger;
import ml.extbukkit.api.scheduler.SchedulerManager;
import ml.extbukkit.api.types.Key;
import ml.extbukkit.api.world.WorldManager;

import java.io.File;
import java.util.Set;
import java.util.UUID;

/**
 * Class, representing the server implementation
 * by the extensions
 */
public abstract class Server {

  @Getter
  private static Server instance;

  /**
   * Sets instance of this class
   *
   * @param instance new instance
   */
  public static void setInstance(Server instance) {
    Preconditions.checkNotNull(instance, "New instance cannot be null");
    Preconditions.checkArgument(Server.instance == null, "Instance already set");
    Server.instance = instance;
  }

  /**
   * Gets the extension loader, from there you can load extensions
   * from different directories than the default one.
   *
   * @return Extension loader
   */
  public abstract ExtensionLoader getExtensionLoader();

  /**
   * Gets the default extension loading directory
   *
   * @return Default loading directory
   */
  public abstract File getExtensionsDir();

  /**
   * Returns the global logger implementation, used from
   * extensions
   *
   * @return overriden logger from the java one
   */
  public abstract Logger getGlobalLogger();

  /**
   * Returns the event manager, where all events are called
   * and handled.
   *
   * @return Event manager
   */
  public abstract EventManager getEventManager();

  /**
   * Returns our scheduler, replacing the bukkit one, probably via
   * a better way.
   *
   * @return Scheduler manager
   */
  public abstract SchedulerManager getSchedulerManager();

  /**
   * Get ExtensionsBukkit jar file
   *
   * @return ExtensionsBukkit file
   */
  public abstract File getExtensionsBukkitFile();

  /**
   * Returns our world manager
   *
   * @return World manager
   */
  public abstract WorldManager getWorldManager();

  /**
   * Returns the command manager, where you can register and match commands
   *
   * @return Command manager
   */
  public abstract CommandManager getCommandManager();

  /**
   * Creates a new key that is used when registering blocks, items...
   *
   * @return new key
   */
  public abstract Key createKey(String namespace, String key);

  /**
   * Stops the server. Requires extension.
   *
   * @param extension extension, requested from
   */
  public abstract void stopServer(Extension extension);

  /**
   * Gets the current server's properties
   *
   * @return Server properties
   */
  public abstract ServerProperties getServerProperties();

  /**
   * Returns the current console
   *
   * @return Console command executor
   */
  public abstract CommandExecutor getConsole();

  /**
   * Returns the extension loader, used into the Extensions
   *
   * @param extensionName extension's name (id)
   * @return extension logger
   */
  public abstract ExtensionLogger getLogger(String extensionName);

  /**
   * Gets all online players
   * <b>This is unmodifiable</b>
   *
   * @return online players
   */
  public abstract Set<ExtensionedPlayer> getPlayers();

  /**
   * Gets the online players count
   *
   * @return players count
   */
  public abstract int getOnlineCount();

  /**
   * Gets a player via its name
   *
   * @param name player name
   * @return player or null
   */
  public abstract ExtensionedPlayer getPlayer(String name);

  /**
   * Gets a player via its UUID
   *
   * @param uuid player uuid
   * @return player or null
   */
  public abstract ExtensionedPlayer getPlayer(UUID uuid);

  /**
   * Loads configuration from file
   *
   * @param file file
   * @return configuration
   */
  public abstract Configuration loadConfiguration(File file);

  /**
   * Gets the cooldown registerer. If you make a {@link ml.extbukkit.api.cooldowns.CooldownManager}, this is
   * needed to register your cooldown manager. Else, when you call {@link CooldownManager#start()}, it won't work
   *
   * @return cooldown manager registerer
   */
  public abstract CooldownManagerRegisterer getCooldownRegisterer();
}