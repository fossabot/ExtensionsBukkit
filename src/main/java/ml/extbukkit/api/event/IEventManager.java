package ml.extbukkit.api.event;

import java.util.Collection;

/**
 * Represents manager, which manages events.
 */
public interface IEventManager {

    /**
     * Sorts all registered handlers for this event by priority and calls them.
     *
     * @param event called event
     */
    void callEvent(Event event);

    /**
     * Register a specific event handler.
     *
     * @param eventClass event's class
     * @param handler Handler
     */
    void registerHandler(Class<? extends Event> eventClass, IHandler handler);

    /**
     * Returns an unmodifiable collection of all registered handlers for the specific event class.
     *
     * @param eventClass the event's class you wish to get the handlers of
     * @return registered handlers for the specific event class
     */
    Collection<IHandler> getHandlers(Class<? extends Event> eventClass);
}