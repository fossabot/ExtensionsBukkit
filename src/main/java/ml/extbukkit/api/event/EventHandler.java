package ml.extbukkit.api.event;

/**
 * Handler class
 *
 * @param <T> Event type class
 */
@FunctionalInterface
public interface EventHandler<T extends Event> {
    /**
     * Ran when selected event is called
     *
     * @param event handled event
     */
    void handle(T event);

    /**
     * Get handle priority
     *
     * @return Handle priority
     */
    default HandlePriority priority() {
        return HandlePriority.NORMAL;
    }
}