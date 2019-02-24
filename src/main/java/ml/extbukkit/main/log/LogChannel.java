package ml.extbukkit.main.log;

import ml.extbukkit.api.log.ILogChannel;

/**
 * Simple implementation of {@link ILogChannel}
 */
public class LogChannel implements ILogChannel {

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
    @Override
    public String getName() {
        return name;
    }
}