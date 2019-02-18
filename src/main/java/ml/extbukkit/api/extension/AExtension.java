package ml.extbukkit.api.extension;

import ml.extbukkit.api.builtin.events.EventDependenciesLoaded;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.api.server.IServerProperties;
import ml.extbukkit.api.types.IKeyMaker;
import ml.extbukkit.api.world.IWorldManager;
import ml.extbukkit.main.server.Server;

import java.io.File;

/**
 * Extension class
 */
public abstract class AExtension {
    private boolean depLoaded = false;
    private File file = null;

    /**
     * Get extension ID
     * Allowed characters: 0-9, a-z, _ (Case insensitive)
     *
     * @return Extension ID
     */
    public abstract String getID();

    /**
     * Get extension display name
     * Default: Extension ID
     *
     * @return Extension name
     */
    public String getName() {
        return getID();
    }

    /**
     * Get extension description
     * Default: None
     *
     * @return Extension description
     */
    public String getDescription() {
        return "";
    }

    /**
     * Get extension authors
     * Default: None
     *
     * @return Extension authors
     */
    public String[] getAuthors() {
        return new String[0];
    }

    /**
     * Get extension dependencies
     * Default: None
     *
     * @return Extension dependencies
     */
    public String[] getDependencies() {
        return new String[0];
    }

    /**
     * Get extension version
     *
     * @return Extension version
     */
    public abstract String getVersion();

    /**
     * Set extension jar file if not set
     *
     * @param file Extension file
     */
    public final void setFile(File file) {
        if (this.file != null) return;
        this.file = file;
    }

    /**
     * Called when all dependencies loaded
     */
    public final void depLoaded() {
        if (!depLoaded) {
            getServer().getEventManager().callEvent(new EventDependenciesLoaded(this));
            depLoaded = true;
        }
    }

    /**
     * Get extension jar file
     *
     * @return Extension file
     */
    public File getFile() {
        return file;
    }

    /**
     * Get server instance
     * Shortcut for: {@link ml.extbukkit.main.server.Server}.getInstance();
     *
     * @return Server instance
     */
    public IServer getServer() {
        return Server.getInstance();
    }

    /**
     * Get logger
     * Shortcut for: {@link ml.extbukkit.main.server.Server}.getInstance().getLogger();
     *
     * @return Logger instance
     */
    public ILogger getLogger() {
        return getServer().getLogger();
    }

    /**
     * Log a message
     * Shortcut for: {@link ml.extbukkit.main.server.Server}.getInstance().getLogger().logSigned(this, message);
     *
     * @param message Message to log
     */
    public void log(String message) {
        getLogger().logSigned(this, message);
    }

    /**
     * Get scheduler manager
     * Shortcut for: {@link ml.extbukkit.main.server.Server}.getInstance().getScheduler();
     *
     * @return Scheduler manager
     */
    public ISchedulerManager getScheduler() {
        return getServer().getSchedulerManager();
    }

    /**
     * Get world manager
     * Shortcut for: {@link ml.extbukkit.main.server.Server}.getInstance().getWorldManager();
     *
     * @return World manager
     */
    public IWorldManager getWorlds() {
        return getServer().getWorldManager();
    }

    /**
     * Get key maker
     * Shortcut for: {@link ml.extbukkit.main.server.Server}.getInstance().getKeyMaker();
     *
     * @return Key maker
     */
    public IKeyMaker getKeys() {
        return getServer().getKeyMaker();
    }

    /**
     * Get extension loader
     * Shortcut for: {@link ml.extbukkit.main.server.Server}.getInstance().getExtensionLoader();
     *
     * @return Extension loader
     */
    public IExtensionLoader getLoader() {
        return getServer().getExtensionLoader();
    }

    /**
     * Get server properties
     * Shortcut for: {@link ml.extbukkit.main.server.Server}.getInstance().getServerProperties();
     *
     * @return Server properties
     */
    public IServerProperties getProperties() {
        return getServer().getServerProperties();
    }
}