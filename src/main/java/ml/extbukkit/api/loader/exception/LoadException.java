package ml.extbukkit.api.loader.exception;

/**
 * Simple runtime exception, thrown when
 * something is wrong with loading on some
 * of the extensions
 */
public class LoadException extends RuntimeException
{

    public LoadException(String message, Throwable cause)
    {
        super( message, cause );
    }

}