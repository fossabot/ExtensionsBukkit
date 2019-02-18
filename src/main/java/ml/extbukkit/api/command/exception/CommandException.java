package ml.extbukkit.api.command.exception;

/**
 * Command exception class
 */
public class CommandException extends RuntimeException {
    /**
     * Command exception
     *
     * @param message Exception message
     * @param cause Throwable
     */
    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}