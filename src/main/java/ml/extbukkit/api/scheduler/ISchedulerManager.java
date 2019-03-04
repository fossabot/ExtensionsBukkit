package ml.extbukkit.api.scheduler;

import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.util.Time;

import java.util.Map;
import java.util.UUID;

/**
 * Scheduler manager class
 */
public interface ISchedulerManager {

    /**
     * Schedules a repeating task until cancelled
     *
     * @param owner Extension
     * @param task Task to run
     * @param delay Delay before start
     * @param interval Interval between running
     * @return UUID of task
     */
    IScheduledTask schedule(AExtension owner, Runnable task, Time delay, Time interval);

    /**
     * Schedules a delayed task.
     * NOTE: Delayed tasks are not stored
     *
     * @param owner Extension
     * @param task Task to run
     * @param delay delay before the task is executed
     */
    void schedule(AExtension owner, Runnable task, Time delay);

    /**
     * Cancel all tasks of an extension
     *
     * @param extension Extension
     */
    void cancelAll(AExtension extension);

    /**
     * Cancel task by its UUID
     *
     * @param uuid Task UUID
     */
    void cancel(UUID uuid);

    /**
     * Gets the specified task by its id
     *
     * @param uuid task's id
     * @return Scheduled task / null if the uuid does not belong to any task
     */
    IScheduledTask getTask(UUID uuid);
}