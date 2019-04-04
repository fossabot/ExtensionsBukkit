package ml.extbukkit.api.event;

/**
 * Preventable event class
 */
public interface Preventable {
    /**
     * Set event prevented or not
     *
     * @param value Is prevented
     */
    void setPrevented(boolean value);

    /**
     * Check if event is prevented
     *
     * @return true, if prevented
     */
    boolean isPrevented();
}