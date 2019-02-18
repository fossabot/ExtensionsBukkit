package ml.extbukkit.api.types;

/**
 * Key class
 */
public interface IKey {
    /**
     * Get key namespace
     *
     * @return Key namespace
     */
    String getNamespace();

    /**
     * Get key name
     *
     * @return Key name
     */
    String getKey();

    /**
     * Convert key to string
     *
     * @return Key string
     */
    String toString();
}