package ml.extbukkit.api.builtin.events;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.extension.Extension;

public class EventAddressed extends Event {

    private String ext;

    public EventAddressed(String extensionID) {
        this.ext = extensionID;
    }

    public EventAddressed(Extension extension) {
        this.ext = extension.getID();
    }

    public String getForID() {
        return ext;
    }

    public boolean check(Extension extension) {
        return extension.getID().equals(ext);
    }
}