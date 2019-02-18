package ml.extbukkit.api.log;

/**
 * Log handler class
 */
public interface ILogHandler {
    /**
     * Ran before log message is sent
     *
     * @param channel Log channel
     * @param message Message string
     * @return Handle result
     */
    IHandleResult log(ILogChannel channel, String message);
}