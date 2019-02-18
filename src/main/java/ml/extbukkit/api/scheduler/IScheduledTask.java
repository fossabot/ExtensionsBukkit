package ml.extbukkit.api.scheduler;

import ml.extbukkit.api.extension.AExtension;

import java.util.UUID;

/**
 * Scheduled task class
 */
public interface IScheduledTask {
    /**
     * Cancel task
     */
    void cancel();

    /**
     * Get task delay in ticks
     *
     * @return Delay in ticks
     */
    long getDelay();

    /**
     * Get task interval in ticks
     *
     * @return Interval in ticks
     */
    long getInterval();

    /**
     * Get task uuid
     *
     * @return Task uuid
     */
    UUID getUUID();

    /**
     * Get task extension
     *
     * @return Extension
     */
    AExtension getOwner();

    ITask getTask();

    TaskType getType();
}