package ml.extbukkit.main.secure.scheduler;

import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.scheduler.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SchedulerManager implements ISchedulerManager {
    private Map<AExtension, Map<UUID, IScheduledTask>> tsks = new HashMap<>();
    @Override
    public UUID schedule(AExtension owner, ITask task, TaskType type, TimeUnit timeUnitDelay, long delay, TimeUnit timeUnitInterval, long interval) {
        if(!tsks.containsKey(owner)) tsks.put(owner, new HashMap<>());
        UUID uuid = UUID.randomUUID();
        if(delay > 0) {
            schedule(owner, () -> {
                tsks.get(owner).put(uuid, new ScheduledTask(delay, interval, timeUnitDelay, timeUnitInterval, owner, task, uuid, type));
            }, TaskType.DELAYED, TimeUnit.TICK, 0, timeUnitDelay, delay);
        }
        else tsks.get(owner).put(uuid, new ScheduledTask(delay, interval, timeUnitDelay, timeUnitInterval, owner, task, uuid, type));
        return uuid;
    }
    @Override
    public UUID schedule(AExtension owner, ITask task, TaskType type, long delay, TimeUnit timeUnitInterval, long interval) {
        return schedule(owner, task, type, TimeUnit.TICK, delay, timeUnitInterval, interval);
    }
    @Override
    public UUID schedule(AExtension owner, ITask task, TaskType type, TimeUnit timeUnitDelay, long delay, long interval) {
        return schedule(owner, task, type, timeUnitDelay, delay, TimeUnit.TICK, interval);
    }
    @Override
    public UUID schedule(AExtension owner, ITask task, TaskType type, TimeUnit timeUnitInterval, long interval) {
        return schedule(owner, task, type, 0, timeUnitInterval, 1);
    }
    @Override
    public UUID schedule(AExtension owner, ITask task, TaskType type, long interval) {
        return schedule(owner, task, type, TimeUnit.TICK, 0, 1);
    }
    @Override
    public UUID schedule(AExtension owner, ITask task, TaskType type, long delay, long interval) {
        return schedule(owner, task, type, delay, TimeUnit.TICK, interval);
    }
    @Override
    public void cancelAll(AExtension extension) {
        tsks.get(extension).clear();
    }
    @Override
    public void cancel(AExtension extension, UUID uuid) {
        tsks.get(extension).remove(uuid);
    }
    @Override
    public Map<AExtension, Map<UUID, IScheduledTask>> getTasks() {
        return tsks;
    }
    @Override
    public IScheduledTask getTask(AExtension extension, UUID uuid) {
        return tsks.get(extension).get(uuid);
    }
}