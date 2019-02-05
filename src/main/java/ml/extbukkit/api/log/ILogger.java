package ml.extbukkit.api.log;

public interface ILogger {
  void info(String message);
  void warning(String message);
  void error(String message);
}