package ml.extbukkit.main.secure.bukkit;

import ml.extbukkit.api.builtin.events.EventLoad;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.main.secure.command.CommandManager;
import ml.extbukkit.main.secure.server.Server;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BukkitExtensionsBukkit extends JavaPlugin {
    private static BukkitExtensionsBukkit I;
    private CommandManager commandManager;

    public BukkitExtensionsBukkit() {
        I = this;
    }

    public static BukkitExtensionsBukkit getInstance() {
        return I;
    }

    @Override
    public void onLoad() {
        Server.getInstance();
        commandManager = (CommandManager) Server.getInstance().getCommandManager();
        /*((Logger) LogManager.getRootLogger()).addFilter( new AbstractFilter()
        {
            @Override
            public Result filter(LogEvent event)
            {
                ((ml.extbukkit.main.secure.log.Logger) Server.getInstance().getLogger()).logBukkit( event.getLevel(), event.getMessage().getFormattedMessage() );
                return Result.DENY;
            }
        } );*/
        if (!getServer().getVersion().contains("1.13")) {
            Server.getInstance().getLogger().log("Unsupported Minecraft version found, we should disable server. Only minecraft 1.13 is supported!");
            getServer().shutdown();
        }
        if (!Server.getInstance().getExtensionsDir().exists()) {
            Server.getInstance().getExtensionsDir().mkdirs();
        }
        Server.getInstance().getExtensionLoader().loadAll(Server.getInstance().getExtensionsDir());
        Server.getInstance().getEventManager().callEvent(new EventLoad());
        Server.getInstance().getExtensionLoader().getExtensions().forEach(AExtension::onEnable);
        ((CommandManager) Server.getInstance().getCommandManager()).registerCommands();
        if (getFile().exists()) {
            getFile().delete();
        }
        Updater.download();
    }

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BukkitEventListener(), this);
        getServer().getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunner(), 0L, 1L);
    }

    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);
    }

    @Override
    public File getFile() {
        return super.getFile();
    }
}
