package ml.extbukkit.api.log;

/**
 * Log channel class
 */
public final class LogChannel {
    /**
     * Log channel name
     */
    private String name;

    /**
     * Creates a new log channel
     *
     * @param name Name of the log channel
     */
    public LogChannel(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the channel
     *
     * @return name
     */
    public String getName() {
        return name;
    }
}