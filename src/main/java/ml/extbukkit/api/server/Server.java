package ml.extbukkit.api.server;

import com.google.common.base.Preconditions;
import lombok.Getter;
import ml.extbukkit.api.command.CommandExecutor;
import ml.extbukkit.api.command.CommandManager;
import ml.extbukkit.api.connection.ExtensionedPlayer;
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
 * ExtensionedServer class
 */
public abstract class Server
{

    @Getter
    private static Server instance;

    /**
     * Sets instance of this class
     *
     * @param instance new instance
     */
    public static void setInstance(Server instance)
    {
        Preconditions.checkNotNull( instance, "New instance cannot be null" );
        Preconditions.checkArgument( Server.instance == null, "Instance already set" );
        Server.instance = instance;
    }

    /**
     * Get extension loader<br>
     * Needed for loading extensions
     *
     * @return Extension loader
     */
    public abstract ExtensionLoader getExtensionLoader();

    /**
     * Get default extensions loading directory<br>
     * Needed for loading extensions from default directory
     *
     * @return Default loading directory
     */
    public abstract File getExtensionsDir();

    /**
     * Gets the global logger
     *
     * @return SimpleLogger
     */
    public abstract Logger getGlobalLogger();

    /**
     * Get event manager<br>
     * Needed for managing events (handling, calling...)
     *
     * @return Event manager
     */
    public abstract EventManager getEventManager();

    /**
     * Get scheduler manager<br>
     * Needed for scheduling tasks
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
     * Get world manager<br>
     * Needed for managing world (setting blocks, getting positions...)
     *
     * @return SimpleWorld manager
     */
    public abstract WorldManager getWorldManager();

    /**
     * Get command manager<br>
     * Needed for registering commands
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
     * Stop the server
     * @deprecated Use {@link #stopServer(Extension)}
     */
    @Deprecated
    public abstract void stopServer();

    /**
     * Stops the server. Requires extension.
     *
     * @param extension extension, requested from
     */
    public abstract void stopServer(Extension extension);

    /**
     * Get server properties
     *
     * @return ExtensionedServer properties
     */
    public abstract ServerProperties getServerProperties();

    /**
     * Get console command executor<br>
     * Needed for executing commands as console
     *
     * @return Console command executor
     */
    public abstract CommandExecutor getConsole();

    /**
     * Gets logger for extension
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
}