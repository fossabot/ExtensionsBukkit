package ml.extbukkit.main.secure.bukkit;

import com.bergerkiller.bukkit.common.events.EntityAddEvent;
import com.bergerkiller.bukkit.common.events.EntityMoveEvent;
import com.bergerkiller.bukkit.common.events.EntityRemoveEvent;
import com.bergerkiller.bukkit.common.events.EntityRemoveFromServerEvent;
import ml.extbukkit.api.builtin.events.*;
import ml.extbukkit.api.command.ACommand;
import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.ITabCompleter;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.main.secure.command.CommandExecutor;
import ml.extbukkit.main.secure.command.CommandManager;
import ml.extbukkit.main.secure.command.PermissionManager;
import ml.extbukkit.main.secure.world.entity.Entity;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BukkitEventListener implements Listener {

    private IServer server = IServer.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldSave(WorldSaveEvent e) {
        server.getEventManager().callEvent(new EventWorldSave(server.getWorldManager().getWorld(e.getWorld().getName())));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldInit(WorldInitEvent e) {
        server.getEventManager().callEvent(new EventWorldInitialize(server.getWorldManager().getWorld(e.getWorld().getName())));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldLoad(WorldLoadEvent e) {
        server.getEventManager().callEvent(new EventWorldLoad(server.getWorldManager().getWorld(e.getWorld().getName())));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldUnload(WorldUnloadEvent e) {
        EventWorldUnload ee = new EventWorldUnload(server.getWorldManager().getWorld(e.getWorld().getName()));
        server.getEventManager().callEvent(ee);
        e.setCancelled(ee.isPrevented());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTabComplete(TabCompleteEvent event) {
        CommandManager manager = CommandManager.getInstance();
        String[] argsRaw = event.getBuffer().split(" ");
        String[] args = Arrays.copyOfRange(argsRaw, 1, argsRaw.length);
        String commandName = argsRaw[0];
        for (Map.Entry<String, ACommand> commandEntry : manager.getCommandMap().entrySet()) {
            if (commandEntry.getValue() instanceof ITabCompleter) {
                ITabCompleter completer = (ITabCompleter) commandEntry.getValue();
                if (commandName.equalsIgnoreCase(commandEntry.getValue().getName())) {
                    ICommandExecutor executor = event.getSender() instanceof ConsoleCommandSender ? server.getConsole() : new CommandExecutor(event.getSender());
                    event.getCompletions().addAll(completer.onTabComplete(executor, args));
                }
            } else {
                event.getCompletions().addAll(tabCompleteDefault(args));
            }
        }
    }

    private List<String> tabCompleteDefault(String[] args) {
        String lastArg;
        String lastArgUp0 = (args.length > 0) ? args[args.length - 1] : "";
        if (lastArgUp0.equalsIgnoreCase("")) {
            lastArg = args[0];
        } else {
            lastArg = lastArgUp0;
        }
        List<String> names = new ArrayList<>();
        Bukkit.getServer().getOnlinePlayers().forEach(player -> names.add(player.getName()));
        return names.stream().filter(a -> a.startsWith(lastArg)).collect(Collectors.toList());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent e) {

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteractEntity(PlayerInteractEntityEvent e) {

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onJoin(PlayerJoinEvent event) {
        EventPlayerJoin ourEvent = new EventPlayerJoin(new Entity(event.getPlayer()), event.getJoinMessage());
        server.getEventManager().callEvent(ourEvent);
        event.setJoinMessage(ourEvent.getJoinMessage());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        EventPlayerQuit ourEvent = new EventPlayerQuit(new Entity(event.getPlayer()), event.getQuitMessage());
        server.getEventManager().callEvent(ourEvent);
        event.setQuitMessage(ourEvent.getQuitMessage());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityAdd(EntityAddEvent e) {
        ((PermissionManager) IServer.getInstance().getPermissionManager()).updatePermissions();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityRemove(EntityRemoveEvent e) {
        ((PermissionManager) IServer.getInstance().getPermissionManager()).updatePermissions();
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityMove(EntityMoveEvent e) {

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityRemoveServer(EntityRemoveFromServerEvent e) {
        ((PermissionManager) IServer.getInstance().getPermissionManager()).updatePermissions();
    }
}