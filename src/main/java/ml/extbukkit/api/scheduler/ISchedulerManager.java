package ml.extbukkit.api.scheduler;

import com.sun.istack.internal.NotNull;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.util.Time;

import java.util.Map;
import java.util.UUID;

/**
 * Scheduler manager class
 */
public interface ISchedulerManager {
    /**
     * Schedule a task
     * In case of type delayed, delay before start will be delay + interval
     *
     * @param owner Extension
     * @param task Task to run
     * @param type Task type
     * @param delay Delay before start
     * @param interval Interval between runs
     * @return UUID of task
     */
    UUID schedule(@NotNull AExtension owner, @NotNull ITask task, @NotNull TaskType type, @NotNull Time delay, @NotNull Time interval);

    /**
     * Schedule a task without delay
     * In case of type delayed, use interval as delay
     *
     * @param owner Extension
     * @param task Task to run
     * @param type Task type
     * @param interval Interval between runs (In case of delayed, delay before run)
     * @return UUID of task
     */
    UUID schedule(@NotNull AExtension owner, @NotNull ITask task, @NotNull TaskType type, @NotNull Time interval);

    /**
     * Cancel all tasks of an extension
     *
     * @param extension Extension
     */
    void cancelAll(@NotNull AExtension extension);

    /**
     * Cancel task by uuid
     *
     * @param extension Extension
     * @param uuid Task UUID
     */
    void cancel(@NotNull AExtension extension, @NotNull UUID uuid);

    /**
     * Get all tasks
     *
     * @return Map of tasks
     */
    Map<String, Map<UUID, IScheduledTask>> getTasks();

    /**
     * Get task by uuid
     *
     * @param extension Extension
     * @param uuid UUID
     * @return Scheduled task
     */
    IScheduledTask getTask(@NotNull AExtension extension, @NotNull UUID uuid);
}