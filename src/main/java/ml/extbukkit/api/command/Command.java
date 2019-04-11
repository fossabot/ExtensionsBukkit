package ml.extbukkit.api.command;

import lombok.Data;

/**
 * Represents a command
 */
@Data
public abstract class Command {
    private String name;
    private String[] aliases;

    /**
     * Creates a command with no aliases
     *
     * @param name Command name
     */
    public Command(String name) {
        this(name, new String[0]);
    }

    /**
     * Creates a command
     *
     * @param name    Command name
     * @param aliases List of aliases
     */
    public Command(String name, String[] aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    /**
     * This method is called when the command is executed
     *
     * @param sender command executor
     * @param alias  exact alias used to invoke the command
     * @param args   command arguments
     */
    public abstract void execute(CommandExecutor sender, String alias, String[] args);
}