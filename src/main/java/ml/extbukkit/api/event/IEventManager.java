package ml.extbukkit.api.event;

import java.util.Collection;

public interface IEventManager {
    void callEvent(Event event);
    void registerContainer(IHandlerContainer container);
    Collection<IMethodPriority> getEventsIn(IHandlerContainer container);
}