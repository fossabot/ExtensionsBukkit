package ml.extbukkit.api.extension;

import ml.extbukkit.api.builtin.events.EventDependenciesLoaded;
import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.api.server.IServerProperties;
import ml.extbukkit.api.types.IKeyMaker;
import ml.extbukkit.api.world.IWorldManager;
import ml.extbukkit.main.server.Server;

import java.io.File;

public abstract class AExtension {
    private boolean depLoaded = false;
    private File file = null;
    public abstract String getID();
    public String getName() {
        return getID();
    }
    public String getDescription() {
        return "";
    }
    public String[] getAuthors() {
        return new String[0];
    }
    public String[] getDependencies() {
        return new String[0];
    }
    public abstract String getVersion();
    public final void setFile(File file) {
        if(this.file != null) return;
        this.file = file;
    }
    public final void depLoaded() {
        if(!depLoaded) {
            getEvents().pullEvent(new EventDependenciesLoaded(this));
            depLoaded = true;
        }
    }
    public final File getFile() {
        return file;
    }
    protected final IServer getServer() {
        return Server.getInstance();
    }
    protected final ILogger getLogger() {
        return getServer().getLogger();
    }
    protected final void log(String message) {
        getLogger().logSigned(this, message);
    }
    protected final IEventManager getEvents() {
        return getServer().getEventManager();
    }
    protected final ICommandManager getCommands() {
        return getServer().getCommandManager();
    }
    protected final ISchedulerManager getScheduler() {
        return getServer().getSchedulerManager();
    }
    protected final IWorldManager getWorlds() {
        return getServer().getWorldManager();
    }
    protected final IKeyMaker getKeys() {
        return getServer().getKeyMaker();
    }
    protected final IExtensionLoader getLoader() {
        return getServer().getExtensionLoader();
    }
    protected final IServerProperties getProperties() {
        return getServer().getServerProperties();
    }
}