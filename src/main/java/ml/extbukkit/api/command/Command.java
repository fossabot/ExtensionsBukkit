package ml.extbukkit.api.command;

import lombok.Data;
import ml.extbukkit.api.extension.AExtension;

@Data
public abstract class Command {
    private String name;
    private String[] aliases;
    private AExtension extension;

    public Command(String name) {
        this(name, new String[0]);
    }

    public Command(String name, String[] aliases) {
        this.name = name;
        this.aliases = aliases;
    }

    public abstract void execute(ICommandExecutor commandExecutor, String command, String[] args);
}