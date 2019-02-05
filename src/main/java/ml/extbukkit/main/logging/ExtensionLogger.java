package ml.extbukkit.main.logging;

import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.main.BukkitExtensionsBukkit;

public class ExtensionLogger implements ILogger {
  private AExtension extension;
  private BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();

  public ExtensionLogger(AExtension extension) {
    this.extension = extension;
  }

  @Override
  public void info(String message) {
    plugin.getLogger().info("[" + extension.getName() + "]: " + message);
  }

  @Override
  public void warning(String message) {
    plugin.getLogger().warning("[" + extension.getName() + "]: " + message);
  }

  @Override
  public void error(String message) {
    plugin.getLogger().severe("[" + extension.getName() + "]: " + message);
  }
}