package ml.extbukkit.api.command;

public interface ICommandExecutor extends IPermissible {
    void sendMessage(String message);
    void sendMessages(String... message);
    String getName();
}