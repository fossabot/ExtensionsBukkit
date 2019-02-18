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
     * Will be executed at the middle of process
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