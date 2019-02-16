package ml.extbukkit.api.util;

/**
 * Represents a wrapper that wraps the specified class.
 * It gives a parameter and a default constructor to the
 * extending class
 *
 * @param <T> wrapped class
 */
public abstract class Wrapper<T>
{
    protected T handle;

    public Wrapper(T handle)
    {
        this.handle = handle;
    }
}