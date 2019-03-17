package ml.extbukkit.main.secure.server;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.ICommandManager;
import ml.extbukkit.api.command.IPermissionManager;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.log.IExtensionLogger;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.api.server.IServerProperties;
import ml.extbukkit.api.types.IKey;
import ml.extbukkit.api.world.IWorldManager;
import ml.extbukkit.api.world.entity.IEntity;
import ml.extbukkit.main.secure.bukkit.BukkitExtensionsBukkit;
import ml.extbukkit.main.secure.command.CommandManager;
import ml.extbukkit.main.secure.command.Console;
import ml.extbukkit.main.secure.command.PermissionManager;
import ml.extbukkit.main.secure.event.EventManager;
import ml.extbukkit.main.secure.log.ExtensionLogger;
import ml.extbukkit.main.secure.log.Logger;
import ml.extbukkit.main.secure.manager.ExtensionLoader;
import ml.extbukkit.main.secure.scheduler.SchedulerManager;
import ml.extbukkit.main.secure.types.Key;
import ml.extbukkit.main.secure.world.WorldManager;
import ml.extbukkit.main.secure.world.entity.Entity;
import org.bukkit.Bukkit;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Server implements IServer {
    private static Server server;
    private ExtensionLoader loader;
    private SchedulerManager scheduler;
    private WorldManager worlds;
    private PermissionManager permissions;
    private File EXTENSIONS = new File("extensions/");
    private ServerProperites properties;
    private BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();
    private EventManager events;
    private Console console;

    private Server() {
        if(server != null) return;
        loader = new ExtensionLoader();
        scheduler = SchedulerManager.getInstance();
        worlds = new WorldManager();
        events = new EventManager();
        properties = new ServerProperites();
        permissions = new PermissionManager();
        console = new Console();
    }

    public static Server getInstance() {
        if(server == null) server = new Server();
        return server;
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
    public IPermissionManager getPermissionManager() {
        return permissions;
    }

    @Override
    public IKey createKey(String namespace, String key) {
        return new Key(namespace, key);
    }

    @Override
    public void stopServer() {
        getGlobalLogger().log(Channels.INFO, "Server is shutting down...");
        plugin.getServer().shutdown();
    }

    @Override
    public void stopServer(AExtension extension) {
        getGlobalLogger().log(Channels.INFO, "Server is shutting down... Extension: " + extension.getName());
        plugin.getServer().shutdown();
    }

    @Override
    public IServerProperties getServerProperties() {
        return properties;
    }

    @Override
    public ICommandExecutor getConsole() {
        return console;
    }

    @Override
    public IExtensionLogger getLogger(String extensionName) {
        return new ExtensionLogger(extensionName);
    }

    @Override
    public Set<IEntity> getPlayers() {
        Set<IEntity> players = new HashSet<>();
        Bukkit.getOnlinePlayers().forEach(player -> players.add(new Entity(player)));
        return Collections.unmodifiableSet(players);
    }

    @Override
    public int getOnlineCount() {
        return getPlayers().size();
    }

    @Override
    public IEntity getPlayer(String name) {
        return new Entity(Bukkit.getPlayer(name));
    }

    @Override
    public IEntity getPlayer(UUID uuid) {
        return new Entity(Bukkit.getPlayer(uuid));
    }

}