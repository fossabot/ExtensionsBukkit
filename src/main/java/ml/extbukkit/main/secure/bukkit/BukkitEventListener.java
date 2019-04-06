package ml.extbukkit.main.secure.bukkit;

import ml.extbukkit.api.builtin.events.EventPlayerJoin;
import ml.extbukkit.api.builtin.events.EventPlayerQuit;
import ml.extbukkit.api.builtin.events.EventWorldInitialize;
import ml.extbukkit.api.builtin.events.EventWorldLoad;
import ml.extbukkit.api.builtin.events.EventWorldSave;
import ml.extbukkit.api.builtin.events.EventWorldUnload;
import ml.extbukkit.api.command.Command;
import ml.extbukkit.api.command.CommandExecutor;
import ml.extbukkit.api.command.TabCompleter;
import ml.extbukkit.api.connection.ExtensionedPlayer;
import ml.extbukkit.api.server.Server;
import ml.extbukkit.main.secure.command.SimpleCommandManager;
import ml.extbukkit.main.secure.command.ExtensionedCommandExecutor;
import ml.extbukkit.main.secure.connection.SimpleExtensionPlayer;
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

  private Server server = Server.getInstance();

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
    e.setCancelled(server.getEventManager().callEvent(ee).isPrevented());
  }

  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onTabComplete(TabCompleteEvent event) {
    SimpleCommandManager manager = SimpleCommandManager.getInstance();
    String[] argsRaw = event.getBuffer().split(" ");
    String[] args = Arrays.copyOfRange(argsRaw, 1, argsRaw.length);
    String commandName = argsRaw[0];
    for(Map.Entry<String, Command> commandEntry : manager.getCommandMap().entrySet()) {
      if(commandEntry.getValue() instanceof TabCompleter) {
        TabCompleter completer = (TabCompleter) commandEntry.getValue();
        if(commandName.equalsIgnoreCase(commandEntry.getValue().getName())) {
          CommandExecutor executor = event.getSender() instanceof ConsoleCommandSender ? Server.getInstance().getConsole() : new ExtensionedCommandExecutor(event.getSender());
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
    if(lastArgUp0.equalsIgnoreCase("")) {
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
    ExtensionedPlayer player = new SimpleExtensionPlayer(event.getPlayer());
    EventPlayerJoin ourEvent = new EventPlayerJoin(player, event.getJoinMessage());
    server.getEventManager().callEvent(ourEvent);
    event.setJoinMessage(ourEvent.getJoinMessage());
    BukkitExtensionsBukkit.getInstance().getServerImplementation().getPlayersUnmodified().add(player);
  }

  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  public void onQuit(PlayerQuitEvent event) {
    ExtensionedPlayer player = new SimpleExtensionPlayer(event.getPlayer());
    EventPlayerQuit ourEvent = new EventPlayerQuit(player, event.getQuitMessage());
    server.getEventManager().callEvent(ourEvent);
    event.setQuitMessage(ourEvent.getQuitMessage());
    BukkitExtensionsBukkit.getInstance().getServerImplementation().getPlayersUnmodified().remove(player);
  }
}