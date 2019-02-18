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
     * Get extension loader
     * Needed for loading extensions
     *
     * @return Extension loader
     */
    IExtensionLoader getExtensionLoader();

    /**
     * Get default extensions loading directory
     * Needed for loading extensions from default directory
     *
     * @return Default loading directory
     */
    File getExtensionsDir();

    /**
     * Get logger
     * Needed for logging messages
     *
     * @return Logger
     */
    ILogger getLogger();

    /**
     * Get event manager
     * Needed for managing events (handling, calling...)
     *
     * @return Event manager
     */
    IEventManager getEventManager();

    /**
     * Get scheduler manager
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
     * Get world manager
     * Needed for managing world (setting blocks, getting positions...)
     *
     * @return World manager
     */
    IWorldManager getWorldManager();

    /**
     * Get command manager
     * Needed for registering commands
     *
     * @return Command manager
     */
    ICommandManager getCommandManager();

    /**
     * Get key maker
     * Needed for making keys (Used in registering blocks, items, etc.)
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
     * Get console command executor
     * Needed for executing commands as console
     *
     * @return Console command executor
     */
    ICommandExecutor getConsole();
}