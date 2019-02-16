package ml.extbukkit.api.command;

import java.util.List;

public interface TabCompleter {
    List<String> onTabComplete(ICommandExecutor sender, String[] args);
}