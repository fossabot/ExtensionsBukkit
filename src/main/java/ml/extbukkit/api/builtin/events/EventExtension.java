package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.extension.Extension;

public class EventExtension extends Event {

    @Getter
    private Extension extension;

    public EventExtension(Extension extension) {
        this.extension = extension;
    }
}