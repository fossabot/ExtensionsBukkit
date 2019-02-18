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
     * Register a handler container
     *
     * @param container Handler container
     */
    void registerContainer(IHandlerContainer container);

    /**
     * Returns an unmodifiable collection of a class called "MethodPriority",
     * containing the priority and the method of a event in the
     * specified container
     * <b>This cannot be modified. If you try modifying it, will throw an {@link UnsupportedOperationException}</b>
     *
     * @param container the event methods container
     * @return unmodifiable collection of methods and their priorities
     */
    Collection<IMethodPriority> getEventsIn(IHandlerContainer container);
}