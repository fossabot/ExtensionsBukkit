package ml.extbukkit.api.log;

import java.util.List;
import java.util.Set;

/**
 * Represents a extension logger
 * Don't gonna explain what what logs
 */
public interface ExtensionLogger {

  void info(String message);

  void info(String... messages);

  void info(List<String> messages);

  void info(Set<String> messages);

  void warn(String message);

  void warn(String... messages);

  void warn(List<String> messages);

  void warn(Set<String> messages);

  void error(String message);

  void error(String message, Throwable stack);

  void debug(String message);

  void debug(String... messages);

  void debug(List<String> messages);

  void debug(Set<String> messages);

  void fatal(String message);

  void fatal(String message, Throwable stack);

  void log(LogChannel channel, String message);

  void log(LogChannel channel, String... messages);

  void log(LogChannel channel, List<String> messages);

  void log(LogChannel channel, Set<String> messages);

  void log(LogChannel channel, String message, Throwable stack);

}