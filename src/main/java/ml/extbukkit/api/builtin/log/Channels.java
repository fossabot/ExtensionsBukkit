package ml.extbukkit.api.builtin.log;

import ml.extbukkit.api.log.ILogChannel;
import ml.extbukkit.main.log.LogChannel;

public class Channels {
    public static final ILogChannel INFO = new LogChannel("INFO");
    public static final ILogChannel WARN = new LogChannel("WARN");
    public static final ILogChannel ERROR = new LogChannel("ERROR");
    public static final ILogChannel DEBUG = new LogChannel("DEBUG");
    public static final ILogChannel FATAL = new LogChannel("FATAL");
}