package ml.extbukkit.api.server;

import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.types.IKeyMaker;
import ml.extbukkit.api.world.IWorldManager;

import java.io.File;

/**
 * Server class
 */
public interface IServer {
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
     * Get logger<br>
     * Needed for logging messages
     *
     * @return Logger
     */
    ILogger getLogger();

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
     * Get key maker<br>
     * Needed for making keys (Used when registering blocks, items, etc.)
     *
     * @return Key maker
     */
    IKeyMaker getKeyMaker();

    /**
     * Stop the server
     */
    void stopServer();

    /**
     * Get server properties
     *
     * @return Server properties
     */
    IServerProperties getServerProperties();

    /**
     * Get console command executor<br>
     * Needed for executing commands as console
     *
     * @return Console command executor
     */
    ICommandExecutor getConsole();
}