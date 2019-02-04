package ml.extbukkit.api.event;

import ml.extbukkit.api.extension.AExtension;

public interface IEventManager {
    Event pullEvent(Event e);
    void registerHandler(AExtension e, Handler h);
}