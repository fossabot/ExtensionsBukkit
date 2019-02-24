package ml.extbukkit.api.builtin.log;

import ml.extbukkit.api.log.ILogChannel;
import ml.extbukkit.main.log.LogChannel;

/**
 * Default channels class
 */
public class Channels {
    /**
     * Default INFO channel, used for information
     */
    public static final ILogChannel INFO = new LogChannel("INFO");

    /**
     * Default WARN channel, used for warnings
     */
    public static final ILogChannel WARN = new LogChannel("WARN");

    /**
     * Default ERROR channel, used for errors
     */
    public static final ILogChannel ERROR = new LogChannel("ERROR");

    /**
     * Default DEBUG channel, used for debugging
     */
    public static final ILogChannel DEBUG = new LogChannel("DEBUG");

    /**
     * Default FATAL channel, used for fatal errors
     */
    public static final ILogChannel FATAL = new LogChannel("FATAL");
}