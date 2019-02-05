package ml.extbukkit.api.command;

public class ExtensionCommandException extends RuntimeException {

  public ExtensionCommandException(String message, Throwable cause) {
    super("[ExtensionException]: " + message, cause);
  }
}