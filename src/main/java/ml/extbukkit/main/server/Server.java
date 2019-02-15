package ml.extbukkit.main.server;

import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.server.IServerProperties;
import ml.extbukkit.api.types.IKeyMaker;
import ml.extbukkit.api.world.IWorldManager;
import ml.extbukkit.main.BukkitExtensionsBukkit;
import ml.extbukkit.main.manager.*;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.main.types.KeyMaker;
import ml.extbukkit.main.world.WorldManager;
import org.bukkit.Bukkit;

import java.io.File;

public class Server implements IServer {
    private EventManager events;
    private ExtensionLoader loader;
    private SchedulerManager scheduler;
    private WorldManager worlds;
    private File EXTENSIONS = new File("extensions/");
    private static Server SERVER = null;
    private CommandManager commands;
    private KeyMaker keys;
    private Logger logger;
    private ServerProperites properties;

    public static IServer getInstance() {
        if(SERVER == null) SERVER = new Server();
        return SERVER;
    }
    private Server() {
        loader = new ExtensionLoader();
        events = new EventManager();
        scheduler = new SchedulerManager();
        worlds = new WorldManager();
        commands = new CommandManager();
        keys = new KeyMaker();
        logger = new Logger();
        properties = new ServerProperites();
    }

    @Override
    public IExtensionLoader getExtensionLoader() {
        return loader;
    }

    @Override
    public File getExtensionsDir() {
        return EXTENSIONS;
    }

    @Override
    public ILogger getLogger() {
        return logger;
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

    @Override
    public ICommandManager getCommandManager() {
        return commands;
    }

    @Override
    public IKeyMaker getKeyMaker() { return keys; }

    @Override
    public void stopServer() {
        Bukkit.shutdown();
    }
    @Override
    public IServerProperties getServerProperties() {
        return properties;
    }
}