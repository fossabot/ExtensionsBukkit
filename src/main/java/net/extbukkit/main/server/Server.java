package net.extbukkit.main.server;

import net.extbukkit.api.event.IEventManager;
import net.extbukkit.api.extension.AExtension;
import net.extbukkit.api.loader.IExtensionLoader;
import net.extbukkit.api.log.ILogger;
import net.extbukkit.api.scheduler.ISchedulerManager;
import net.extbukkit.api.server.IServer;
import net.extbukkit.api.world.IWorldManager;
import net.extbukkit.main.BukkitExtensionsBukkit;
import net.extbukkit.main.manager.EventManager;
import net.extbukkit.main.manager.ExtensionLoader;
import net.extbukkit.main.manager.SchedulerManager;
import net.extbukkit.main.manager.WorldManager;

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