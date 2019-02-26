package ml.extbukkit.api.event;

/**
 * Represents a priorities
 */
public enum HandlePriority {
    /**
     * Last to get executed
     * <b>By last we do not mean exactly last</b>
     */
    LOWEST,
    /**
     * Before Last to get executed
     */
    LOW,
    /**
     * Will be executed in the middle of handling<br>
     * This is a default value
     */
    NORMAL,
    /**
     * Before first to get executed
     */
    HIGH,
    /**
     * First to get executed
     * <b>By first we do not mean exactly first</b>
     */
    HIGHEST
}