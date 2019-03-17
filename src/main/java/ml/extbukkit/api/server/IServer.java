package ml.extbukkit.api.server;

import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.command.IPermissionManager;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.log.IExtensionLogger;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.types.IKey;
import ml.extbukkit.api.world.IWorldManager;
import ml.extbukkit.api.world.entity.IEntity;
import ml.extbukkit.main.secure.server.Server;

import java.io.File;
import java.util.Set;
import java.util.UUID;

/**
 * ExtensionedServer class
 */
public interface IServer {
    static IServer getInstance() {
        return Server.getInstance();
    }

    /**
     * Get extension loader<br>
     * Needed for loading extensions
     *
     * @return Extension loader
     */
    IExtensionLoader getExtensionLoader();

    /**
     * Get default extensions loading directory<br>
     * Needed for loading extensions from default directory
     *
     * @return Default loading directory
     */
    File getExtensionsDir();

    /**
     * Gets the global logger
     *
     * @return Logger
     */
    ILogger getGlobalLogger();

    /**
     * Get event manager<br>
     * Needed for managing events (handling, calling...)
     *
     * @return Event manager
     */
    IEventManager getEventManager();

    /**
     * Get scheduler manager<br>
     * Needed for scheduling tasks
     *
     * @return Scheduler manager
     */
    ISchedulerManager getSchedulerManager();

    /**
     * Get ExtensionsBukkit jar file
     *
     * @return ExtensionsBukkit file
     */
    File getExtensionsBukkitFile();

    /**
     * Get world manager<br>
     * Needed for managing world (setting blocks, getting positions...)
     *
     * @return World manager
     */
    IWorldManager getWorldManager();

    /**
     * Get command manager<br>
     * Needed for registering commands
     *
     * @return Command manager
     */
    ICommandManager getCommandManager();

    /**
     * Get permission manager<br>
     * Needed for registering own permission providers
     *
     * @return
     */
    IPermissionManager getPermissionManager();

    /**
     * Creates a new key that is used when registering blocks, items...
     *
     * @return new key
     */
    IKey createKey(String namespace, String key);

    /**
     * Stop the server<br>
     * Use stopServer(extension) instead
     */
    void stopServer();

    /**
     * Stops the server. Requires extension.
     *
     * @param extension extension, requested from
     */
    void stopServer(AExtension extension);

    /**
     * Get server properties
     *
     * @return ExtensionedServer properties
     */
    IServerProperties getServerProperties();

    /**
     * Get console command executor<br>
     * Needed for executing commands as console
     *
     * @return Console command executor
     */
    ICommandExecutor getConsole();

    /**
     * Gets logger for extension
     *
     * @param extensionName extension's name (id)
     * @return extension logger
     */
    IExtensionLogger getLogger(String extensionName);

    /**
     * Gets all online players
     * <b>This is unmodifiable</b>
     *
     * @return online players
     */
    Set<IEntity> getPlayers();

    /**
     * Gets the online players count
     *
     * @return players count
     */
    int getOnlineCount();

    /**
     * Gets a player via its name
     *
     * @param name player name
     * @return player or null
     */
    IEntity getPlayer(String name);

    /**
     * Gets a player via its UUID
     *
     * @param uuid player uuid
     * @return player or null
     */
    IEntity getPlayer(UUID uuid);
}