package ml.extbukkit.main;

import ml.extbukkit.api.builtin.events.EventWorldInitialize;
import ml.extbukkit.api.builtin.events.EventWorldLoad;
import ml.extbukkit.api.builtin.events.EventWorldSave;
import ml.extbukkit.api.builtin.events.EventWorldUnload;
import ml.extbukkit.main.server.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.event.world.WorldUnloadEvent;

public class BukkitEventListener implements Listener {
    @EventHandler
    public void onWorldSave(WorldSaveEvent e) {
        Server.getInstance().getEventManager().pullEvent(new EventWorldSave(null));
    }
    @EventHandler
    public void onWorldInit(WorldInitEvent e) {
        Server.getInstance().getEventManager().pullEvent(new EventWorldInitialize(null));
    }
    @EventHandler
    public void onWorldLoad(WorldLoadEvent e) {
        Server.getInstance().getEventManager().pullEvent(new EventWorldLoad(null));
    }
    @EventHandler
    public void onWorldUnload(WorldUnloadEvent e) {
        EventWorldUnload ee = (EventWorldUnload) Server.getInstance().getEventManager().pullEvent(new EventWorldUnload(null));
        e.setCancelled(ee.isPrevented());
    }
}