package ml.extbukkit.main;

import com.google.gson.JsonObject;
import ml.extbukkit.api.builtin.events.EventWorldInitialize;
import ml.extbukkit.api.builtin.events.EventWorldLoad;
import ml.extbukkit.api.builtin.events.EventWorldSave;
import ml.extbukkit.api.builtin.events.EventWorldUnload;
import ml.extbukkit.api.command.Command;
import ml.extbukkit.api.command.CommandExecutor;
import ml.extbukkit.api.command.TabCompleter;
import ml.extbukkit.main.manager.CommandManager;
import ml.extbukkit.main.manager.command.SimpleCommandExecutor;
import ml.extbukkit.main.nms.NBTUtils;
import ml.extbukkit.main.server.Server;
import ml.extbukkit.main.world.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.event.world.WorldSaveEvent;
import org.bukkit.event.world.WorldUnloadEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BukkitEventListener implements Listener
{
    private BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldSave(WorldSaveEvent e)
    {
        Server.getInstance().getEventManager().callEvent( new EventWorldSave( new World( e.getWorld() ) ) );
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldInit(WorldInitEvent e)
    {
        Server.getInstance().getEventManager().callEvent( new EventWorldInitialize( new World( e.getWorld() ) ) );
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldLoad(WorldLoadEvent e)
    {
        Server.getInstance().getEventManager().callEvent( new EventWorldLoad( new World( e.getWorld() ) ) );
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onWorldUnload(WorldUnloadEvent e)
    {
        EventWorldUnload ee = new EventWorldUnload( new World( e.getWorld() ) );
        Server.getInstance().getEventManager().callEvent( ee );
        e.setCancelled( ee.isPrevented() );
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onTabComplete(TabCompleteEvent event)
    {
        CommandManager extensionManager = plugin.getCommandManager();
        String[] rawArgs = event.getBuffer().split( " " );
        String name = rawArgs[0].substring( 1 );
        String[] args = Arrays.copyOfRange( rawArgs, 1, rawArgs.length );
        if ( args.length == 0 )
        {
            for ( String commandCompletes : extensionManager.getCommandMap().keySet() )
            {
                event.getCompletions().add( commandCompletes );
            }
        }
        Command matchedCommand = extensionManager.matchCommand( name );
        List<String> completions;
        if ( !(matchedCommand instanceof TabCompleter) )
        {
            completions = tabCompleteDefault( args );
            for ( String completion : completions )
            {
                event.getCompletions().add( completion );
            }
            return;
        }
        TabCompleter completer = (TabCompleter) matchedCommand;
        CommandExecutor executor = event.getSender() instanceof ConsoleCommandSender ? Server.getInstance().getConsole() : new SimpleCommandExecutor( event.getSender() );
        if ( completer.onTabComplete( executor, args ) == null )
        {
            completions = tabCompleteDefault( args );
        }
        else
        {
            completions = completer.onTabComplete( executor, args );
        }
        for ( String completion : completions )
        {
            event.getCompletions().add( completion );
        }
    }

    private List<String> tabCompleteDefault(String[] args)
    {
        String lastArg;
        String lastArgUp0 = (args.length > 0) ? args[args.length - 1] : "";
        if ( lastArgUp0.equalsIgnoreCase( "" ) )
        {
            lastArg = args[0];
        }
        else
        {
            lastArg = lastArgUp0;
        }
        List<String> names = new ArrayList<>();
        plugin.getServer().getOnlinePlayers().forEach( player -> names.add( player.getName() ) );
        return names.stream().filter( a -> a.startsWith( lastArg ) ).collect( Collectors.toList() );
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteract(PlayerInteractEvent e)
    {
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onInteractEntity(PlayerInteractEntityEvent e)
    {
        JsonObject nbt = NBTUtils.nbtToJson( NBTUtils.getEntityNbt( e.getRightClicked() ) );
        nbt.addProperty( "Fire", "20s" );
        NBTUtils.setEntityNbt( e.getRightClicked(), NBTUtils.jsonToNbt( nbt ) );
    }
}