package net.extbukkit.api.event;

import net.extbukkit.api.extension.AExtension;

public interface IEventManager {
    Event pullEvent(Event e);
    void registerHandler(AExtension e, Handler h);
}