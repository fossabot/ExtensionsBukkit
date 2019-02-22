package ml.extbukkit.main.secure.bukkit;

import ml.extbukkit.api.builtin.events.EventLoad;
import ml.extbukkit.main.secure.command.CommandManager;
import ml.extbukkit.main.secure.server.Server;
import net.minecraft.server.v1_13_R2.World;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BukkitExtensionsBukkit extends JavaPlugin {
    private static BukkitExtensionsBukkit I;
    private ml.extbukkit.main.secure.log.Logger extensionsLogger = ml.extbukkit.main.secure.log.Logger.getInstance();

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
            return;
        }
        ((Logger) LogManager.getRootLogger()).addFilter( new AbstractFilter() {
            @Override
            public Result filter(LogEvent event) {
                extensionsLogger.logBukkit(event.getLevel(), event.getMessage().getFormattedMessage());
                if(event.getThrown() != null)
                    extensionsLogger.logBukkit(event.getLevel(), ExceptionUtils.getStackTrace(event.getThrown()));
                return Result.DENY;
            }
        });
        if (!getServer().getVersion().contains("1.13")) {
            Server.getInstance().getLogger().log("Unsupported Minecraft version found, we should disable server. Only minecraft 1.13 is supported!");
            getServer().shutdown();
        }
        if (!Server.getInstance().getExtensionsDir().exists()) {
            Server.getInstance().getExtensionsDir().mkdirs();
        }
        Server.getInstance().getExtensionLoader().loadAll(Server.getInstance().getExtensionsDir());
        Server.getInstance().getEventManager().callEvent(new EventLoad());
        CommandManager.getInstance().registerCommands();
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
        extensionsLogger.closeLog();
    }

    @Override
    public File getFile() {
        return super.getFile();
    }
}
