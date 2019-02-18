package ml.extbukkit.api.command;

import lombok.Data;
import ml.extbukkit.api.extension.AExtension;

/**
 * Command class
 */
@Data
public abstract class Command {
    private String name;
    private String[] aliases;

    /**
     * Command
     *
     * @param name Command name
     */
    public Command(String name) {
        this(name, new String[0]);
    }

    /**
     * Command
     *
     * @param name Command name
     * @param aliases List of aliases
     */
    public Command(String name, String[] aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    /**
     * Execute command as someone
     *
     * @param executor Executor
     * @param command Command
     * @param arguments Arguments
     */
    public abstract void execute(ICommandExecutor executor, String command, String[] arguments);
}