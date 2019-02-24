package ml.extbukkit.api.log;

import ml.extbukkit.api.extension.AExtension;

import java.util.List;

/**
 * Logger class
 */
public interface ILogger {
    /**
     * Send a signed log message to console<br>
     * Recommended for extensions
     *
     * @param extension Extension
     * @param channel Log channel
     * @param message Log message
     */
    void logSigned(AExtension extension, ILogChannel channel, String message);

    /**
     * Send a signed log message with an INFO channel to console<br>
     * Recommended for extensions
     *
     * @param extension Extension
     * @param message Log message
     */
    void logSigned(AExtension extension, String message);

    /**
     * Send a log message with an an INFO channel to console
     *
     * @param message Log message
     */
    void log(String message);

    /**
     * Send a log message to console
     *
     * @param channel Log channel
     * @param message Log message
     */
    void log(ILogChannel channel, String message);

    /**
     * Register a log handler
     *
     * @param handler Log handler
     */
    void registerLogHandler(ILogHandler handler);

    /**
     * Get list of log handlers
     *
     * @return List of log handlers
     */
    List<ILogHandler> getLogHandlers();

    /**
     * Logs a stacktrace with {@link ml.extbukkit.api.builtin.log.Channels#ERROR} channel
     *
     * @param message error message (maybe internal?)
     * @param t error (maybe internal?)
     */
    void logStack(String message, Throwable t);

    void logStackSigned(AExtension extension, String message, Throwable t);
}