package ml.extbukkit.api.scheduler.exception;

public class RunnableException extends RuntimeException
{

    public RunnableException(String message, Throwable t)
    {
        super( message, t );
    }

}