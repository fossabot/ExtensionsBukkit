package ml.extbukkit.main.secure.bukkit;

import java.io.File;

import ml.extbukkit.api.builtin.events.EventLoad;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.main.secure.command.CommandManager;
import ml.extbukkit.main.secure.log.util.LevelToChannel;
import ml.extbukkit.main.secure.server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.bukkit.plugin.java.JavaPlugin;

public final class BukkitExtensionsBukkit extends JavaPlugin {
    private static BukkitExtensionsBukkit I;
    private ml.extbukkit.main.secure.log.Logger extensionsLogger = ml.extbukkit.main.secure.log.Logger.getInstance();
    private IServer server = Server.getInstance();

    public BukkitExtensionsBukkit() {
        I = this;
    }

    public static BukkitExtensionsBukkit getInstance() {
        return I;
    }

    @Override
    public void onLoad() {
        try {
            Class.forName("com.bergerkiller.bukkit.common.internal.CommonPlugin");
        } catch (ClassNotFoundException e) {
            getLogger().severe("BKCommonLib is not installed, but is required by ExtensionsBukkit!");
            getLogger().severe("Downloading BKCommonLib...");
            Updater.downloadBKCL();
            getLogger().warning( "BKCommonLib has been downloaded, disabling..." );
            getServer().getPluginManager().disablePlugin( this );
            return;
        }
        if (!getServer().getVersion().contains("1.13")) {
            extensionsLogger.log("Unsupported Minecraft version found. Only minecraft 1.13 is supported! Disabling...");
            getServer().getPluginManager().disablePlugin( this );
            return;
        }
        ((Logger) LogManager.getRootLogger()).addFilter( new AbstractFilter() {
            @Override
            public Result filter(LogEvent event) {
                if ( event.getMessage().getFormattedMessage().contains( "ExtensionsBukkit" ) )
                {
                    extensionsLogger.log( LevelToChannel.transform( event.getLevel() ), event.getMessage().getFormattedMessage() );
                    if( event.getThrown() != null )
                    {
                        extensionsLogger.logStack( event.getThrown().getMessage(), event.getThrown() );
                    }
                } else
                {
                    extensionsLogger.logBukkit( event.getLevel(), event.getMessage().getFormattedMessage() );
                    if( event.getThrown() != null )
                    {
                        extensionsLogger.logBukkitStack( event.getThrown() );
                    }
                }
                return Result.DENY;
            }
        });
        if (!server.getExtensionsDir().exists()) {
            server.getExtensionsDir().mkdirs();
        }
        server.getExtensionLoader().loadAll(server.getExtensionsDir());
        server.getEventManager().callEvent(new EventLoad());
        CommandManager.getInstance().registerCommands();
        if (getFile().exists()) {
            getFile().delete();
        }
        Updater.download();
    }

    @Override
    public void onEnable() {
        if ( !getServer().getPluginManager().isPluginEnabled( this ) )
        {
            return;
        }
        getServer().getPluginManager().registerEvents(new BukkitEventListener(), this);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunner(), 0L, 1L);
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
        IExtensionLoader extensionLoader = server.getExtensionLoader();
        extensionLoader.getExtensions().forEach( extensionLoader::disable );
        extensionsLogger.closeLog();
    }

    @Override
    public File getFile() {
        return super.getFile();
    }
}
