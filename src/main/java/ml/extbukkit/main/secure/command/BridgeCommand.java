package ml.extbukkit.main.secure.command;

import ml.extbukkit.api.command.ICommandExecutor;
import ml.extbukkit.api.command.exception.CommandException;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.server.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

public class BridgeCommand extends Command
{
    private ml.extbukkit.api.command.Command command;
    private AExtension extension;

    public BridgeCommand(ml.extbukkit.api.command.Command our, AExtension extension)
    {
        super( our.getName() );
        this.command = our;
        this.extension = extension;
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings)
    {
        ICommandExecutor executor = commandSender instanceof ConsoleCommandSender ? Server.getInstance().getConsole() : new CommandExecutor(commandSender);
        try
        {
            command.execute( executor, s, strings );
        } catch ( Throwable t )
        {
            throw new CommandException( "Internal exception executing command '/" + s + "' in extension " + extension.getName(), t );
        }
        return true;
    }

}