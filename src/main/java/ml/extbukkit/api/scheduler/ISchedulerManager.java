package ml.extbukkit.api.scheduler;

import java.util.Map;
import java.util.UUID;

import com.google.common.collect.Multimap;
import ml.extbukkit.api.extension.AExtension;

public interface ISchedulerManager {
    UUID schedule(AExtension owner, ITask task, TaskType type, TimeUnit timeUnitDelay, long delay, TimeUnit timeUnitInterval, long interval);
    UUID schedule(AExtension owner, ITask task, TaskType type, long delay, TimeUnit timeUnitInterval, long interval);
    UUID schedule(AExtension owner, ITask task, TaskType type, TimeUnit timeUnitDelay, long delay, long interval);
    UUID schedule(AExtension owner, ITask task, TaskType type, TimeUnit timeUnitInterval, long interval);
    UUID schedule(AExtension owner, ITask task, TaskType type, long interval);
    UUID schedule(AExtension owner, ITask task, TaskType type, long delay, long interval);
    void cancelAll(AExtension extension);
    void cancel(AExtension extension, UUID uuid);
    Map<AExtension, Map<UUID, IScheduledTask>> getTasks();
    IScheduledTask getTask(AExtension extension, UUID uuid);
}