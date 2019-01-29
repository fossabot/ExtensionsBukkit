package net.extbukkit.main;

import net.extbukkit.main.server.Server;
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
        if(!Server.getInstance().getExtensionsDir().exists())
            Server.getInstance().getExtensionsDir().mkdirs();
        Server.getInstance().getExtensionLoader().loadAll(Server.getInstance().getExtensionsDir());
    }
    @Override
    public void onEnable() {

    }
    @Override
    public void onDisable() {

    }
}