package ml.extbukkit.api.builtin.events;

import ml.extbukkit.api.extension.Extension;

/**
 * Event, called when extension is disabled
 */
public class EventExtensionDisable extends EventExtension {

  public EventExtensionDisable(Extension extension) {
    super(extension);
  }

}