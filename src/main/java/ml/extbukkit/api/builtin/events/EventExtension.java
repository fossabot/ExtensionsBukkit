package ml.extbukkit.api.builtin.events;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.extension.AExtension;

public class EventExtension extends Event {
    AExtension extension;
    public EventExtension(AExtension extension) {
        this.extension = extension;
    }
    public AExtension getExtension() {
        return extension;
    }
}