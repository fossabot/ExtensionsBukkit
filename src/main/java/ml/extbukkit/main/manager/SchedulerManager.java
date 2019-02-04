package ml.extbukkit.main.manager;

import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.scheduler.Task;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SchedulerManager implements ISchedulerManager {
    private Map<UUID, Task> tasks = new HashMap<>();

    @Override
    public UUID register(Task t) {
        UUID uuid = UUID.randomUUID();
        tasks.put(uuid, t);
        return uuid;
    }

    @Override
    public boolean isRunning(UUID uuid) {
        return tasks.containsKey(uuid);
    }

    @Override
    public void stop(UUID uuid) {
        if(tasks.containsKey(uuid)) tasks.remove(uuid);
    }

    @Override
    public Map<UUID, Task> getTasks() {
        return tasks;
    }
}