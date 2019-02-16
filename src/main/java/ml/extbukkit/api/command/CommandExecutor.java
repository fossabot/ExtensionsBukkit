package ml.extbukkit.api.command;

public interface CommandExecutor
{
    void sendMessage(String message);
    void sendMessages(String... message);
    boolean hasPermission(String permission);
    String getName();
}