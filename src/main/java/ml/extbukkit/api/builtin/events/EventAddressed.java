package ml.extbukkit.api.builtin.events;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.extension.AExtension;

public class EventAddressed extends Event {

    private String ext;

    public EventAddressed(String extensionID) {
        this.ext = extensionID;
    }

    public EventAddressed(AExtension extension) {
        this.ext = extension.getID();
    }

    public String getForID() {
        return ext;
    }

    public boolean check(AExtension extension) {
        return extension.getID().equals(ext);
    }
}