package ml.extbukkit.api.types;

/**
 * Key maker class
 */
public interface IKeyMaker {
    /**
     * Make a new key
     *
     * @param namespace Key namespace
     * @param key Key name
     * @return New key
     */
    IKey key(String namespace, String key);
}