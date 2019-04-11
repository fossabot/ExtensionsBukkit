package ml.extbukkit.api.event;

import java.util.Collection;

/**
 * Represents manager, which manages events.
 */
public interface EventManager {

    /**
     * Sorts all registered handlers for this event by priority and calls them.
     *
     * @param event called event
     */
    <T extends Event> T callEvent(T event);

    /**
     * Register a specific event handler.
     *
     * @param eventClass event's class
     * @param handler    Handler
     */
    <T extends Event> void registerHandler(Class<T> eventClass, EventHandler<T> handler);

    /**
     * Returns an unmodifiable collection of all registered handlers for the specific event class.
     *
     * @param eventClass the event's class you wish to get the handlers of
     * @return registered handlers for the specific event class
     */
    Collection<EventHandler<? extends Event>> getHandlers(Class<? extends Event> eventClass);
}