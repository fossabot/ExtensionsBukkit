package ml.extbukkit.api.event;

import java.lang.reflect.Method;

/**
 * Represents a class, containing information about a specific
 * event method in a specific container.
 * Obtained by {@link IEventManager#getEventsIn(IHandlerContainer)}
 * and {@link IEventManager#getMethodsFor(Class)}
 */
public interface IMethodPriority {

    /**
     * Calls the event method with container and event specified
     *
     * @param container event container
     * @param event event
     */
    void invoke(IHandlerContainer container, Event event);

    /**
     * The called method.
     *
     * @return called method
     */
    Method getMethod();

    /**
     * Gets the priority of the specified event method.
     *
     * @return priority
     */
    HandlePriority getPriority();

}