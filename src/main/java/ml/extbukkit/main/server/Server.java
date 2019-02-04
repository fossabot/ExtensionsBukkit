package ml.extbukkit.main.server;

import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.world.IWorldManager;
import ml.extbukkit.main.BukkitExtensionsBukkit;
import ml.extbukkit.main.manager.ExtensionLoader;
import ml.extbukkit.main.manager.SchedulerManager;
import ml.extbukkit.main.manager.WorldManager;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.main.manager.EventManager;

import java.io.File;

public class Server implements IServer {
    private EventManager events;
    private ExtensionLoader loader;
    private SchedulerManager scheduler;
    private WorldManager worlds;
    private File EXTENSIONS = new File("extensions/");
    private static Server SERVER = null;

    public static IServer getInstance() {
        if(SERVER == null) SERVER = new Server();
        return SERVER;
    }
    private Server() {
        loader = new ExtensionLoader();
        events = new EventManager();
        scheduler = new SchedulerManager();
        worlds = new WorldManager();
    }

    @Override
    public IExtensionLoader getExtensionLoader() {
        return loader;
    }

    @Override
    public File getExtensionsDir() {
        return EXTENSIONS;
    }

    //TODO Add loggers
    @Override
    public ILogger getLogger(AExtension extension) {
        return null;
    }

    @Override
    public IEventManager getEventManager() {
        return events;
    }

    @Override
    public ISchedulerManager getSchedulerManager() {
        return scheduler;
    }

    @Override
    public File getExtensionsBukkitFile() {
        return BukkitExtensionsBukkit.getInstance().getFile();
    }

    @Override
    public IWorldManager getWorldManager() {
        return worlds;
    }
}