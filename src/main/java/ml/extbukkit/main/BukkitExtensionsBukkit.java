package ml.extbukkit.main;

import ml.extbukkit.api.builtin.events.EventLoad;
import ml.extbukkit.api.command.CommandExecutor;
import ml.extbukkit.api.command.exception.CommandException;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.main.manager.CommandManager;
import ml.extbukkit.main.manager.command.SimpleCommandExecutor;
import ml.extbukkit.main.server.Server;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;

public final class BukkitExtensionsBukkit extends JavaPlugin
{
    private static BukkitExtensionsBukkit I;
    private CommandMap bcmp;
    private CommandManager commandManager;

    public BukkitExtensionsBukkit()
    {
        I = this;
    }

    public static BukkitExtensionsBukkit getInstance()
    {
        return I;
    }

    @Override
    public void onLoad()
    {
        commandManager = new CommandManager();
        /*((Logger) LogManager.getRootLogger()).addFilter( new AbstractFilter()
        {
            @Override
            public Result filter(LogEvent event)
            {
                ((ml.extbukkit.main.manager.Logger) Server.getInstance().getLogger()).logBukkit( event.getLevel(), event.getMessage().getFormattedMessage() );
                return Result.DENY;
            }
        } );*/
        if ( !Bukkit.getVersion().contains( "1.13" ) )
        {
            Server.getInstance().getLogger().log( "Unsupported Minecraft version found, we should disable server because of the extensions..." );
            Bukkit.shutdown();
        }
        if ( !Server.getInstance().getExtensionsDir().exists() )
        {
            Server.getInstance().getExtensionsDir().mkdirs();
        }
        Server.getInstance().getExtensionLoader().loadAll( Server.getInstance().getExtensionsDir() );
        Server.getInstance().getEventManager().callEvent( new EventLoad() );
        Server.getInstance().getExtensionLoader().getExtensions().forEach( AExtension::onEnable );
        registerCommands();
        if ( getFile().exists() )
        {
            getFile().delete();
        }
//        Updater.download();
    }

    @Override
    public void onEnable()
    {
        Bukkit.getPluginManager().registerEvents( new BukkitEventListener(), this );
        Bukkit.getScheduler().scheduleSyncRepeatingTask( this, new BukkitRunner(), 0L, 1L );
    }

    @Override
    public void onDisable()
    {
        Bukkit.getScheduler().cancelTasks( this );
    }

    @Override
    public File getFile()
    {
        return super.getFile();
    }

    public CommandManager getCommandManager()
    {
        return commandManager;
    }

    private void registerCommands()
    {
        try
        {
            Field field = getServer().getClass().getDeclaredField( "commandMap" );
            field.setAccessible( true );
            bcmp = (CommandMap) field.get( getServer() );
        } catch ( IllegalAccessException | NoSuchFieldException e )
        {
            e.printStackTrace();
        }
        commandManager.getCommandsByExtension().asMap().forEach( (extension, registeredCommands) -> registeredCommands.forEach( command ->
        {
            String registeredName = this.getName() + ":" + extension.getName();
            String description = command.getDescription() == null ? "" : command.getDescription();
            String usage = command.getUsage() == null ? "" : command.getUsage();
            bcmp.register( command.getName(), registeredName, new Command( command.getName(), description, usage, Collections.emptyList() )
            {
                @Override
                public boolean execute(CommandSender commandSender, String s, String[] strings)
                {
                    CommandExecutor executor = commandSender instanceof ConsoleCommandSender ? Server.getInstance().getConsole() : new SimpleCommandExecutor( commandSender );
                    if ( !command.hasPermission( executor ) )
                    {
                        executor.sendMessage( "§cYou don't have permission to perform this command!" ); // TODO: Configurable
                        return true;
                    }
                    try
                    {
                        command.execute( executor, strings );
                    } catch ( Throwable t )
                    {
                        throw new CommandException( "Internal exception executing command '/" + s + "' in extension " + command.getExtension().getFullName(), t );
                    }
                    return true;
                }
            } );
        } ) );
    }
}
