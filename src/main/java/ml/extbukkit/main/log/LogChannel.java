package ml.extbukkit.main.log;

import ml.extbukkit.api.log.ILogChannel;

/**
 * Simple log channel class
 */
public class LogChannel implements ILogChannel {
    private String name;

    /**
     * Creates a new log channel
     *
     * @param name Name of the log channer
     */
    public LogChannel(String name) {
        this.name = name;
    }
    @Override
    public String getName() {
        return name;
    }
}