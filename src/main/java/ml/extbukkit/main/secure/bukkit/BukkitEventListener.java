package ml.extbukkit.main.secure.bukkit;

import com.bergerkiller.bukkit.common.events.EntityAddEvent;
import com.bergerkiller.bukkit.common.events.EntityMoveEvent;
import com.bergerkiller.bukkit.common.events.EntityRemoveFromServerEvent;

import ml.extbukkit.api.builtin.events.EventEntityJoin;
import ml.extbukkit.api.builtin.events.EventEntityQuit;
import ml.extbukkit.api.builtin.events.EventWorldInitialize;
import ml.extbukkit.api.builtin.events.EventWorldLoad;
import ml.extbukkit.api.builtin.events.EventWorldSave;
import ml.extbukkit.api.builtin.events.EventWorldUnload;
import ml.extbukkit.api.command.Command;
import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.TabCompleter;
import ml.extbukkit.main.secure.command.CommandManager;
import ml.extbukkit.main.secure.command.CommandExecutor;
import ml.extbukkit.main.secure.world.entity.Entity;
import ml.extbukkit.main.secure.server.Server;
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
import java.util.stream.Collectors;

public class BukkitEventListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldSave(WorldSaveEvent e) {
        Server.getInstance().getEventManager().callEvent(new EventWorldSave(Server.getInstance().getWorldManager().getWorld(e.getWorld().getName())));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldInit(WorldInitEvent e) {
        Server.getInstance().getEventManager().callEvent(new EventWorldInitialize(Server.getInstance().getWorldManager().getWorld(e.getWorld().getName())));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldLoad(WorldLoadEvent e) {
        Server.getInstance().getEventManager().callEvent(new EventWorldLoad(Server.getInstance().getWorldManager().getWorld(e.getWorld().getName())));
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldUnload(WorldUnloadEvent e) {
        EventWorldUnload ee = new EventWorldUnload(Server.getInstance().getWorldManager().getWorld(e.getWorld().getName()));
        Server.getInstance().getEventManager().callEvent(ee);
        e.setCancelled(ee.isPrevented());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTabComplete(TabCompleteEvent event) {
        CommandManager extensionManager = (CommandManager) Server.getInstance().getCommandManager();
        String[] rawArgs = event.getBuffer().split(" ");
        String name = rawArgs[0].substring(1);
        String[] args = Arrays.copyOfRange(rawArgs, 1, rawArgs.length);
        if (args.length == 0) {
            for (String commandCompletes : extensionManager.getCommandMap().keySet()) {
                event.getCompletions().add(commandCompletes);
            }
        }
        Command matchedCommand = extensionManager.matchCommand(name);
        List<String> completions;
        if (!(matchedCommand instanceof TabCompleter)) {
            completions = tabCompleteDefault(args);
            for (String completion : completions) {
                event.getCompletions().add(completion);
            }
            return;
        }
        TabCompleter completer = (TabCompleter) matchedCommand;
        ICommandExecutor executor = event.getSender() instanceof ConsoleCommandSender ? Server.getInstance().getConsole() : new CommandExecutor(event.getSender());
        if (completer.onTabComplete(executor, args) == null) {
            completions = tabCompleteDefault(args);
        } else {
            completions = completer.onTabComplete(executor, args);
        }
        for (String completion : completions) {
            event.getCompletions().add(completion);
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
        EventEntityJoin ourEvent = new EventEntityJoin(new Entity(event.getPlayer()), event.getJoinMessage());
        Server.getInstance().getEventManager().callEvent(ourEvent);
        event.setJoinMessage(ourEvent.getJoinMessage());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onQuit(PlayerQuitEvent event) {
        EventEntityQuit ourEvent = new EventEntityQuit(new Entity(event.getPlayer()), event.getQuitMessage());
        Server.getInstance().getEventManager().callEvent(ourEvent);
        event.setQuitMessage(ourEvent.getQuitMessage());
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityAdd(EntityAddEvent e) {

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityRemove(EntityAddEvent e) {

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityMove(EntityMoveEvent e) {

    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onEntityRemoveServer(EntityRemoveFromServerEvent e) {

    }
}