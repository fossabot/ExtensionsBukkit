package ml.extbukkit.api.command;

/**
 * Command and TabCompleter at once
 */
public abstract class ATabExecutor extends ACommand implements ITabCompleter {

    /**
     * Creates a command with no aliases
     *
     * @param name Command name
     */
    public ATabExecutor(String name) {
        super(name);
    }

    /**
     * Creates a command
     *
     * @param name Command name
     * @param aliases List of aliases
     */
    public ATabExecutor(String name, String[] aliases) {
        super(name, aliases);
    }
}