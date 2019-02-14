package ml.extbukkit.api.builtin.events;

import ml.extbukkit.api.extension.AExtension;

public class EventExtensionReload extends EventAddressed {
    public EventExtensionReload(AExtension extension) {
        super(extension);
    }
    public EventExtensionReload(String extensionID) {
        super(extensionID);
    }
}