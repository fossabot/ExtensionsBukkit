package ml.extbukkit.main;

import ml.extbukkit.api.builtin.events.EventWorldInitialize;
import ml.extbukkit.api.builtin.events.EventWorldLoad;
import ml.extbukkit.api.builtin.events.EventWorldSave;
import ml.extbukkit.api.builtin.events.EventWorldUnload;
import ml.extbukkit.api.command.ICommand;
import ml.extbukkit.main.server.Server;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import java.util.Arrays;
import java.util.List;

public class BukkitEventListener implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldSave(WorldSaveEvent e) {
        Server.getInstance().getEventManager().pullEvent(new EventWorldSave(null));
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldInit(WorldInitEvent e) {
        Server.getInstance().getEventManager().pullEvent(new EventWorldInitialize(null));
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldLoad(WorldLoadEvent e) {
        Server.getInstance().getEventManager().pullEvent(new EventWorldLoad(null));
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldUnload(WorldUnloadEvent e) {
        EventWorldUnload ee = (EventWorldUnload) Server.getInstance().getEventManager().pullEvent(new EventWorldUnload(null));
        e.setCancelled(ee.isPrevented());
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String[] parts = e.getMessage().split(" ");
        String command = parts[0].substring(1);
        List<String> args = Arrays.asList(Arrays.copyOfRange(parts, 1, parts.length));
        ICommand cmd = Server.getInstance().getCommandManager().match(command);
        if(cmd != null)
            cmd.execute(null, command, args);
    }
    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTabComplete(TabCompleteEvent e) {
        String[] parts = e.getBuffer().split(" ");
        String command = parts[0].substring(1);
        List<String> args = Arrays.asList(Arrays.copyOfRange(parts, 1, parts.length));
        if(args.size() == 0)
            for(ICommand cmd : Server.getInstance().getCommandManager().getCommands())
                e.getCompletions().addAll(cmd.getCommands());
        ICommand cmd = Server.getInstance().getCommandManager().match(command);
        if(cmd != null)
            e.getCompletions().addAll(cmd.complete(null, command, args));
    }
}