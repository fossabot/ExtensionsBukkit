package ml.extbukkit.api.log;

/**
 * Represents a extension logger
 * Don't gonna explain what what logs
 */
public interface IExtensionLogger
{

    void info(String message);

    void warn(String message);

    void error(String message);

    void error(String message, Throwable stack);

    void debug(String message);

    void fatal(String message);

    void fatal(String message, Throwable stack);

    void log(ILogChannel channel, String message);

    void log(ILogChannel channel, String message, Throwable stack);

}