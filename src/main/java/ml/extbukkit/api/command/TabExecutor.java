package ml.extbukkit.api.command;

/**
 * Command and TabCompleter at once
 */
public abstract class TabExecutor extends Command implements TabCompleter {
    /**
     * Tab executor
     *
     * @param name Command name
     */
    public TabExecutor(String name) {
        super(name);
    }

    /**
     * Tab executor
     *
     * @param name Command name
     * @param aliases List of aliases
     */
    public TabExecutor(String name, String[] aliases) {
        super(name, aliases);
    }
}