package ml.extbukkit.api.event.exception;

/**
 * Event exception class
 */
public class EventException extends RuntimeException {
    /**
     * Event exeception
     *
     * @param message Exception message
     * @param t Throwable
     */
    public EventException(String message, Throwable t) {
        super(message, t);
    }
}
