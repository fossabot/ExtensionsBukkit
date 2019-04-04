package ml.extbukkit.api.log;

/**
 * Log handle result class
 */
public interface HandleResult {
    /**
     * Get message sent
     *
     * @return Message sent
     */
    String getMessage();

    /**
     * Get log channel
     *
     * @return Log channel
     */
    LogChannel getChannel();

    /**
     * Send it or not
     *
     * @return true, if send
     */
    boolean send();
}