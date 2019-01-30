package net.extbukkit.api.event;

import net.extbukkit.api.extension.Extension;

public interface IEventManager {
    Event pullEvent(Event e);
    void registerHandler(Extension e, Handler h);
}