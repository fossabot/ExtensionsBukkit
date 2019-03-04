package ml.extbukkit.api.builtin.log;

import ml.extbukkit.api.log.LogChannel;

/**
 * Default channels class
 */
public class Channels {
    /**
     * Default INFO channel, used for information
     */
    public static final LogChannel INFO = new LogChannel("INFO");

    /**
     * Default WARN channel, used for warnings
     */
    public static final LogChannel WARN = new LogChannel("WARN");

    /**
     * Default ERROR channel, used for errors
     */
    public static final LogChannel ERROR = new LogChannel("ERROR");

    /**
     * Default DEBUG channel, used for debugging
     */
    public static final LogChannel DEBUG = new LogChannel("DEBUG");

    /**
     * Default FATAL channel, used for fatal errors
     */
    public static final LogChannel FATAL = new LogChannel("FATAL");
}