package ml.extbukkit.api.log;

public interface ILogHandler {
    IHandleResult log(ILogChannel channel, String message);
}