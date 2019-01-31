package net.extbukkit.api.scheduler;

import java.util.Map;
import java.util.UUID;

public interface ISchedulerManager {
    UUID register(Task t);
    boolean isRunning(UUID uuid);
    void stop(UUID uuid);
    Map<UUID, Task> getTasks();
}