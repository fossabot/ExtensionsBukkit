package ml.extbukkit.api.log;

/**
 * Log handler class
 */
public interface LogHandler {
    /**
     * Ran before log message is sent
     *
     * @param channel Log channel
     * @param message Message string
     * @return Handle result
     */
    HandleResult log(LogChannel channel, String message);
}