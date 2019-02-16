package ml.extbukkit.main.manager.command;

import ml.extbukkit.api.command.CommandExecutor;
import ml.extbukkit.api.util.Wrapper;
import org.bukkit.command.ConsoleCommandSender;

public class Console extends Wrapper<ConsoleCommandSender> implements CommandExecutor
{
    public Console(ConsoleCommandSender handle)
    {
        super( handle );
    }

    @Override
    public void sendMessage(String message)
    {
        handle.sendMessage( message );
    }

    @Override
    public void sendMessages(String... message)
    {
        handle.sendMessage( message );
    }

    @Override
    public boolean hasPermission(String permission)
    {
        return handle.hasPermission( permission );
    }

    @Override
    public String getName()
    {
        return handle.getName();
    }
}