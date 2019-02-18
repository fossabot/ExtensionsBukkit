package ml.extbukkit.api.event;

/**
 * Handle priorities
 */
public enum HandlePriority {
    /**
     * Will be executed last
     */
    LOWEST,
    /**
     * Will be executed almost last
     */
    LOW,
    /**
     * Will be executed in the middle of handling<br>
     * This is a default value
     */
    NORMAL,
    /**
     * Will be executed almost first
     */
    HIGH,
    /**
     * Will be executed first
     */
    HIGHEST
}