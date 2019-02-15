package ml.extbukkit.api.log;

public interface IHandleResult {
    String getMessage();
    ILogChannel getChannel();
    boolean send();
}