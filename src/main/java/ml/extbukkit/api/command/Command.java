package ml.extbukkit.api.command;

import lombok.Data;
import ml.extbukkit.api.extension.AExtension;

@Data
public abstract class Command
{

    private String name;
    private String permission;
    private String[] aliases;
    private AExtension extension;
    private String usage;
    private String description;

    public Command(String name)
    {
        this ( name, null, new String[0]);
    }

    public Command(String name, String permission, String[] aliases)
    {
        this.name = name;
        this.permission = permission;
        this.aliases = aliases;
    }

    public abstract void execute(CommandExecutor sender, String[] args);

    public boolean hasPermission(CommandExecutor sender)
    {
        return permission == null || permission.isEmpty() || sender.hasPermission( permission );
    }
}