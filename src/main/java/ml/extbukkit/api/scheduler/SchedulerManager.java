package ml.extbukkit.api.scheduler;

import ml.extbukkit.api.extension.Extension;
import ml.extbukkit.api.util.Time;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * Scheduler manager class
 */
public interface SchedulerManager {

    /**
     * Schedules a repeating task until cancelled
     *
     * @param owner    Extension
     * @param task     Task to run
     * @param delay    Delay before start
     * @param interval Interval between running
     * @return task
     */
    ScheduledTask schedule(Extension owner, Runnable task, Time delay, Time interval);

    /**
     * Schedules a repeating task until cancelled
     *
     * @param owner        the owner of the scheduler
     * @param callbackTask a consumer, which acts like a runnable,
     *                     but giving you access to the scheduled
     *                     task directly
     * @param delay        delay before start
     * @param interval     interval between running
     */
    void schedule(Extension owner, Consumer<ScheduledTask> callbackTask, Time delay, Time interval);

    /**
     * Schedules a delayed task.
     * NOTE: Delayed tasks are not stored
     *
     * @param owner Extension
     * @param task  Task to run
     * @param delay delay before the task is executed
     */
    void schedule(Extension owner, Runnable task, Time delay);

    /**
     * Cancel all tasks of an extension
     *
     * @param extension Extension
     */
    void cancelAll(Extension extension);

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
    ScheduledTask getTask(UUID uuid);
}