package ml.extbukkit.main.secure.server;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.chat.ChatMessage;
import ml.extbukkit.api.chat.TextColor;
import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.connection.ExtensionedPlayer;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.log.IExtensionLogger;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.server.IServerProperties;
import ml.extbukkit.api.server.Server;
import ml.extbukkit.api.types.IKey;
import ml.extbukkit.api.world.IWorldManager;
import ml.extbukkit.main.secure.bukkit.BukkitExtensionsBukkit;
import ml.extbukkit.main.secure.command.CommandManager;
import ml.extbukkit.main.secure.command.Console;
import ml.extbukkit.main.secure.connection.SimpleExtensionPlayer;
import ml.extbukkit.main.secure.event.EventManager;
import ml.extbukkit.main.secure.log.ExtensionLogger;
import ml.extbukkit.main.secure.log.Logger;
import ml.extbukkit.main.secure.manager.ExtensionLoader;
import ml.extbukkit.main.secure.scheduler.SchedulerManager;
import ml.extbukkit.main.secure.types.Key;
import ml.extbukkit.main.secure.world.WorldManager;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ExtensionedServer extends Server
{

    private ExtensionLoader loader;
    private SchedulerManager scheduler;
    private WorldManager worlds;
    private File EXTENSIONS = new File("extensions/");
    private ServerProperites properties;
    private BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();
    private EventManager events;
    private Console console;

    public ExtensionedServer() {
        loader = new ExtensionLoader();
        scheduler = SchedulerManager.getInstance();
        worlds = new WorldManager();
        events = new EventManager();
        properties = new ServerProperites();
        console = new Console();
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
    public IKey createKey(String namespace, String key)
    {
        return new Key( namespace, key );
    }

    @Override
    public void stopServer() {
        plugin.getServer().shutdown();
    }

    @Override
    public void stopServer(AExtension extension)
    {
        getGlobalLogger().log( Channels.WARN, "'" + extension.getName() + "' has requested server stop. Stopping the server in 5 seconds" );
        for ( ExtensionedPlayer online : getPlayers() )
        {
            if ( online.isOp() )
            {
                online.sendMessage( ChatMessage.builder()
                        .message( "EXTENSIONSBUKKIT: '" + extension.getName() + "' requested a server stop. Stopping the server in 5 seconds" )
                        .color( TextColor.RED )
                        .build() );
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
        return console;
    }

    @Override
    public IExtensionLogger getLogger(String extensionName)
    {
        return new ExtensionLogger( extensionName );
    }

    @Override
    public Set<ExtensionedPlayer> getPlayers()
    {
        return Collections.unmodifiableSet( getPlayersUn() );
    }

    public Set<ExtensionedPlayer> getPlayersUn()
    {
        Set<ExtensionedPlayer> players = new HashSet<>();
        Bukkit.getOnlinePlayers().forEach( player -> players.add( new SimpleExtensionPlayer( player ) ) );
        return players;
    }

    @Override
    public int getOnlineCount()
    {
        return getPlayers().size();
    }

    @Override
    public ExtensionedPlayer getPlayer(String name)
    {
        return new SimpleExtensionPlayer( name );
    }

    @Override
    public ExtensionedPlayer getPlayer(UUID uuid)
    {
        return new SimpleExtensionPlayer( uuid );
    }

}