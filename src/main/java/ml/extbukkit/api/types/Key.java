package ml.extbukkit.api.types;

/**
 * NamespacedKey class
 */
public interface Key {
    /**
     * Get key namespace
     *
     * @return NamespacedKey namespace
     */
    String getNamespace();

    /**
     * Get key name
     *
     * @return NamespacedKey name
     */
    String getKey();

    /**
     * Convert key to string
     *
     * @return NamespacedKey string
     */
    String toString();
}