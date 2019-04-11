package ml.extbukkit.api.log;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Represents a extension logger
 * Don't gonna explain what what logs
 */
public interface ExtensionLogger {

    void info(String message);

    void info(String... messages);

    void info(Collection<String> messages);

    void warn(String message);

    void warn(String... messages);

    void warn(Collection<String> messages);

    void error(String message);

    void error(String message, Throwable stack);

    void debug(String message);

    void debug(String... messages);

    void debug(Collection<String> messages);

    void fatal(String message);

    void fatal(String message, Throwable stack);

    void log(LogChannel channel, String message);

    void log(LogChannel channel, String... messages);

    void log(LogChannel channel, Collection<String> messages);

    void log(LogChannel channel, String message, Throwable stack);

}