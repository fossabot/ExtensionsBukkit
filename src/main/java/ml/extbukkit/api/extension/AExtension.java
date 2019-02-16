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

    public abstract void onEnable();
    public final void setFile(File file) {
        if(this.file != null) return;
        this.file = file;
    }

    public final void depLoaded() {
        if(!depLoaded) {
            getServer().getEventManager().callEvent(new EventDependenciesLoaded(this));
            depLoaded = true;
        }
    }

    public File getFile() {
        return file;
    }
    public IServer getServer() {
        return Server.getInstance();
    }

    public ILogger getLogger() {
        return getServer().getLogger();
    }
    public void log(String message) {
        getLogger().logSigned(this, message);
    }

    public ISchedulerManager getScheduler() {
        return getServer().getSchedulerManager();
    }

    public IWorldManager getWorlds() {
        return getServer().getWorldManager();
    }

    public IKeyMaker getKeys() {
        return getServer().getKeyMaker();
    }

    public IExtensionLoader getLoader() {
        return getServer().getExtensionLoader();
    }

    public IServerProperties getProperties() {
        return getServer().getServerProperties();
    }

    public String getFullName() {
        return getName() + " v" + getVersion();
    }
}