package ml.extbukkit.api.log;

import ml.extbukkit.api.extension.Extension;

import java.util.List;

/**
 * SimpleLogger class
 */
public interface Logger {
    /**
     * Send a signed log message to console<br>
     * Recommended for extensions
     *
     * @param extension Extension
     * @param channel   Log channel
     * @param message   Log message
     */
    void logSigned(Extension extension, LogChannel channel, String message);

    /**
     * Send a signed log message with an INFO channel to console<br>
     * Recommended for extensions
     *
     * @param extension Extension
     * @param message   Log message
     */
    void logSigned(Extension extension, String message);

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
    void log(LogChannel channel, String message);

    /**
     * Register a log handler
     *
     * @param handler Log handler
     */
    void registerLogHandler(LogHandler handler);

    /**
     * Get list of log handlers
     *
     * @return List of log handlers
     */
    List<LogHandler> getLogHandlers();

    /**
     * Logs a stacktrace with {@link ml.extbukkit.api.builtin.log.Channels#ERROR} channel
     *
     * @param message error message (maybe internal?)
     * @param t       error (maybe internal?)
     */
    void logStack(String message, Throwable t);

    void logStackSigned(Extension extension, String message, Throwable t);
}