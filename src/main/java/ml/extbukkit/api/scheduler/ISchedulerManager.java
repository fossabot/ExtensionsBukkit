package ml.extbukkit.api.scheduler;

import java.util.Map;
import java.util.UUID;

import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.util.Time;

public interface ISchedulerManager {
    UUID schedule(AExtension owner, ITask task, TaskType type, Time delay, Time interval);
    UUID schedule(AExtension owner, ITask task, TaskType type, Time interval);
    void cancelAll(AExtension extension);
    void cancel(AExtension extension, UUID uuid);
    Map<String, Map<UUID, IScheduledTask>> getTasks();
    IScheduledTask getTask(AExtension extension, UUID uuid);
}