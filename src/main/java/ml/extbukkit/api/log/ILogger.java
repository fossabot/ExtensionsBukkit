package ml.extbukkit.api.log;

import java.util.Arrays;

public interface ILogger {
  void info(String message);
  void warning(String message);
  void error(String message);

  default void info(String... messages) {
    Arrays.stream(messages).forEach(this::info);
  }

  default void warning(String... messages) {
    Arrays.stream(messages).forEach(this::warning);
  }

  default void error(String... messages) {
    Arrays.stream(messages).forEach(this::error);
  }
}