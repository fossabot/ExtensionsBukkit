package ml.extbukkit.main.secure.server;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.log.IExtensionLogger;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.server.IServerProperties;
import ml.extbukkit.api.types.IKeyMaker;
import ml.extbukkit.api.world.IWorldManager;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.main.secure.bukkit.BukkitExtensionsBukkit;
import ml.extbukkit.main.secure.command.CommandManager;
import ml.extbukkit.main.secure.event.EventManager;
import ml.extbukkit.main.secure.log.ExtensionLogger;
import ml.extbukkit.main.secure.log.Logger;
import ml.extbukkit.main.secure.manager.ExtensionLoader;
import ml.extbukkit.main.secure.scheduler.SchedulerManager;
import ml.extbukkit.main.secure.types.KeyMaker;
import ml.extbukkit.main.secure.world.WorldManager;
import org.bukkit.entity.Player;

import java.io.File;

public class Server implements IServer {

    private ExtensionLoader loader;
    private SchedulerManager scheduler;
    private WorldManager worlds;
    private File EXTENSIONS = new File("extensions/");
    private static Server SERVER = null;
    private KeyMaker keys;
    private ServerProperites properties;
    private BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();
    private EventManager events;

    public static IServer getInstance() {
        if(SERVER == null) SERVER = new Server();
        return SERVER;
    }
    private Server() {
        loader = new ExtensionLoader();
        scheduler = new SchedulerManager();
        worlds = new WorldManager();
        keys = new KeyMaker();
        events = new EventManager();
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
    public ILogger getGlobalLogger() {
        return Logger.getInstance();
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
        return plugin.getFile();
    }

    @Override
    public IWorldManager getWorldManager() {
        return worlds;
    }

    @Override
    public ICommandManager getCommandManager() {
        return CommandManager.getInstance();
    }

    @Override
    public IKeyMaker getKeyMaker() { return keys; }

    @Override
    public void stopServer() {
        plugin.getServer().shutdown();
    }

    @Override
    public void stopServer(AExtension extension)
    {
        getGlobalLogger().log( Channels.WARN, "'" + extension.getName() + "' has requested server stop. Stopping the server in 5 seconds" );
        for ( Player online : plugin.getServer().getOnlinePlayers() )
        {
            if ( online.isOp() )
            {
                online.sendMessage( "§cEXTENSIONSBUKKIT: '" + extension.getName() + "' requested a server stop. Stopping the server in 5 seconds" );
            }
        }
        plugin.getServer().getScheduler().scheduleSyncDelayedTask( plugin, () -> plugin.getServer().shutdown(),100 );
    }

    @Override
    public IServerProperties getServerProperties() {
        return properties;
    }

    @Override
    public ICommandExecutor getConsole()
    {
        return null;
    }

    @Override
    public IExtensionLogger getLogger(String extensionName)
    {
        return new ExtensionLogger( extensionName );
    }

}