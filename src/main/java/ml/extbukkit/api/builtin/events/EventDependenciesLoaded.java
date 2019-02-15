package ml.extbukkit.api.builtin.events;

import ml.extbukkit.api.extension.AExtension;

public class EventDependenciesLoaded extends EventAddressed {
    public EventDependenciesLoaded(String extensionID) {
        super(extensionID);
    }
    public EventDependenciesLoaded(AExtension extension) {
        super(extension);
    }
}