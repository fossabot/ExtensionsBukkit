package ml.extbukkit.api.event.exception;

/**
 * Represents a simple exception
 */
public class EventException extends RuntimeException {

    public EventException(String message, Throwable t) {
        super(message, t);
    }
}
