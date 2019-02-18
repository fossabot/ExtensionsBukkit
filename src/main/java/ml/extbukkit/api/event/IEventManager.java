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

    //TODO Javadoc
    Collection<IMethodPriority> getEventsIn(IHandlerContainer container);
}