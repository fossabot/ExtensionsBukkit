package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.extension.AExtension;

public class EventExtension extends Event {
    @Getter
    private AExtension extension;

    public EventExtension(AExtension extension) {
        this.extension = extension;
    }
}