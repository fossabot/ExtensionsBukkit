package ml.extbukkit.api.event;

import java.util.Collection;

/**
 * Event manager class
 */
public interface IEventManager {
    /**
     * Call an event
     *
     * @param event Event to call
     */
    void callEvent(Event event);

    /**
     * Register a handler
     *
     * @param handler Handler
     */
    void registerHandler(Class<? extends Event> eventClass, IHandler handler);
}