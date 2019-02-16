package ml.extbukkit.api.command;

public abstract class TabExecutor extends Command implements TabCompleter
{

    public TabExecutor(String name)
    {
        super( name );
    }

    public TabExecutor(String name, String permission, String[] aliases)
    {
        super( name, permission, aliases );
    }
}
