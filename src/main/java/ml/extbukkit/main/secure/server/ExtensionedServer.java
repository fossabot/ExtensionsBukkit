package ml.extbukkit.main.secure.server;

import ml.extbukkit.api.builtin.log.Channels;
import ml.extbukkit.api.command.CommandExecutor;
import ml.extbukkit.api.command.CommandManager;
import ml.extbukkit.api.config.Configuration;
import ml.extbukkit.api.connection.ExtensionedPlayer;
import ml.extbukkit.api.cooldowns.CooldownManagerRegisterer;
import ml.extbukkit.api.event.EventManager;
import ml.extbukkit.api.extension.Extension;
import ml.extbukkit.api.loader.ExtensionLoader;
import ml.extbukkit.api.log.ExtensionLogger;
import ml.extbukkit.api.log.Logger;
import ml.extbukkit.api.scheduler.SchedulerManager;
import ml.extbukkit.api.server.ServerProperties;
import ml.extbukkit.api.server.Server;
import ml.extbukkit.api.types.Key;
import ml.extbukkit.api.world.WorldManager;
import ml.extbukkit.main.secure.bukkit.BukkitExtensionsBukkit;
import ml.extbukkit.main.secure.command.Console;
import ml.extbukkit.main.secure.command.SimpleCommandManager;
import ml.extbukkit.main.secure.config.YAMLConfiguration;
import ml.extbukkit.main.secure.connection.SimpleExtensionPlayer;
import ml.extbukkit.main.secure.cooldowns.SimpleRegisterer;
import ml.extbukkit.main.secure.event.ExtensionEventManager;
import ml.extbukkit.main.secure.log.SimpleExtensionLogger;
import ml.extbukkit.main.secure.log.SimpleLogger;
import ml.extbukkit.main.secure.manager.SimpleExtensionLoader;
import ml.extbukkit.main.secure.scheduler.SimpleScheduler;
import ml.extbukkit.main.secure.types.NamespacedKey;
import ml.extbukkit.main.secure.world.Worlds;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class ExtensionedServer extends Server {

    private SimpleExtensionLoader loader;
    private SimpleScheduler scheduler;
    private Worlds worlds;
    private File EXTENSIONS = new File("extensions/");
    private ExtensionedServerProperites properties;
    private BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();
    private ExtensionEventManager events;
    private Console console;
    private SimpleRegisterer cooldownManagerRegisterer;
    private SimpleLogger logger;

    public ExtensionedServer() {
        this.logger = SimpleLogger.getInstance();
        loader = new SimpleExtensionLoader(logger);
        scheduler = SimpleScheduler.getInstance();
        worlds = new Worlds();
        events = new ExtensionEventManager(logger);
        properties = new ExtensionedServerProperites();
        console = new Console();
        cooldownManagerRegisterer = new SimpleRegisterer();
    }

    @Override
    public ExtensionLoader getExtensionLoader() {
        return loader;
    }

    @Override
    public File getExtensionsDir() {
        return EXTENSIONS;
    }

    @Override
    public Logger getGlobalLogger() {
        return logger;
    }

    @Override
    public EventManager getEventManager() {
        return events;
    }

    @Override
    public SchedulerManager getSchedulerManager() {
        return scheduler;
    }

    @Override
    public File getExtensionsBukkitFile() {
        return plugin.getFile();
    }

    @Override
    public WorldManager getWorldManager() {
        return worlds;
    }

    @Override
    public CommandManager getCommandManager() {
        return SimpleCommandManager.getInstance();
    }

    @Override
    public Key createKey(String namespace, String key) {
        return new NamespacedKey(namespace, key);
    }

    @Override
    public void stopServer(Extension extension) {
        getGlobalLogger().log(Channels.WARN, "'" + extension.getName() + "' has requested server stop. Stopping the server in 5 seconds");
        for (Player online : plugin.getServer().getOnlinePlayers()) {
            if (online.isOp()) {
                online.sendMessage("§cEXTENSIONSBUKKIT: '" + extension.getName() + "' requested a server stop. Stopping the server in 5 seconds");
            }
        }
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> plugin.getServer().shutdown(), 100);
    }

    @Override
    public ServerProperties getServerProperties() {
        return properties;
    }

    @Override
    public CommandExecutor getConsole() {
        return console;
    }

    @Override
    public ExtensionLogger getLogger(String extensionName) {
        return new SimpleExtensionLogger(extensionName);
    }

    @Override
    public Set<ExtensionedPlayer> getPlayers() {
        return Collections.unmodifiableSet(getPlayersUnmodified());
    }

    public Set<ExtensionedPlayer> getPlayersUnmodified() {
        Set<ExtensionedPlayer> players = new HashSet<>();
        Bukkit.getOnlinePlayers().forEach(player -> players.add(new SimpleExtensionPlayer(player)));
        return players;
    }

    @Override
    public int getOnlineCount() {
        return getPlayers().size();
    }

    @Override
    public ExtensionedPlayer getPlayer(String name) {
        return new SimpleExtensionPlayer(name);
    }

    @Override
    public ExtensionedPlayer getPlayer(UUID uuid) {
        return new SimpleExtensionPlayer(uuid);
    }

    @Override
    public Configuration loadConfiguration(final File file) {
        return new YAMLConfiguration(YamlConfiguration.loadConfiguration(file), file);
    }

    @Override
    public CooldownManagerRegisterer getCooldownRegisterer() {
        return cooldownManagerRegisterer;
    }

}