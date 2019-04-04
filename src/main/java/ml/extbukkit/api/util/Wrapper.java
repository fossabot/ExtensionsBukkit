package ml.extbukkit.api.util;

/**
 * Represents a wrapper that wraps the specified class.<br>
 * It gives a parameter and a default constructor to the extending class
 *
 * @param <T> wrapped class
 */
public abstract class Wrapper<T> {
    /**
     * Instance of wrapped class
     */
    protected T handle;

    /**
     * Wrapper constructor
     *
     * @param handle Instance of wrapped class
     */
    public Wrapper(T handle) {
        this.handle = handle;
    }
}