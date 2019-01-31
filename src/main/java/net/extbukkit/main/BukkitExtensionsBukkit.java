package net.extbukkit.main;

import net.extbukkit.api.builtin.events.EventLoad;
import net.extbukkit.main.server.Server;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class BukkitExtensionsBukkit extends JavaPlugin {
    private static BukkitExtensionsBukkit I;

    public BukkitExtensionsBukkit() {
        I = this;
    }

    public static BukkitExtensionsBukkit getInstance() {
        return I;
    }

    @Override
    public void onLoad() {
        Server.getInstance();
        if(!Server.getInstance().getExtensionsDir().exists())
            Server.getInstance().getExtensionsDir().mkdirs();
        Server.getInstance().getExtensionLoader().loadAll(Server.getInstance().getExtensionsDir());

        Server.getInstance().getEventManager().pullEvent(new EventLoad());
    }
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new BukkitEventListener(), this);
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new BukkitRunner(), 0L, 1L);
    }
    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
    }
}