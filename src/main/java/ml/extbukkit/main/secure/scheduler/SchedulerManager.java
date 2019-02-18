package ml.extbukkit.main.secure.scheduler;

import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.scheduler.*;
import ml.extbukkit.api.util.Time;
import ml.extbukkit.api.util.Validator;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SchedulerManager implements ISchedulerManager {
    private Map<String, Map<UUID, IScheduledTask>> tsks = new HashMap<>();
    @Override
    public UUID schedule(AExtension owner, ITask task, TaskType type, Time delay, Time interval) {
        if(Validator.isNull(owner, task, type, delay, interval) || Validator.isNegative(delay.toLong(), interval.toLong())) return null;
        if(!tsks.containsKey(owner.getID())) tsks.put(owner.getID(), new HashMap<>());
        UUID uuid = UUID.randomUUID();
        tsks.get(owner.getID()).put(uuid, new ScheduledTask(delay.getTicks(), interval.getTicks(), owner, task, uuid, type));
        return uuid;
    }
    @Override
    public UUID schedule(AExtension owner, ITask task, TaskType type, Time interval) {
        return schedule(owner, task, type, new Time(), interval);
    }
    @Override
    public void cancelAll(AExtension extension) {
        if(Validator.isNull(extension) || !tsks.containsKey(extension.getID()) || tsks.get(extension.getID()) == null || tsks.get(extension.getID()).isEmpty()) return;
        tsks.get(extension.getID()).clear();
    }
    @Override
    public void cancel(AExtension extension, UUID uuid) {
        if(Validator.isNull(extension, uuid) || !tsks.containsKey(extension.getID()) || tsks.get(extension.getID()) == null || !tsks.get(extension.getID()).containsKey(uuid) ||  tsks.get(extension.getID()).get(uuid) == null) return;
        tsks.get(extension.getID()).remove(uuid);
    }
    @Override
    public Map<String, Map<UUID, IScheduledTask>> getTasks() {
        return tsks;
    }
    @Override
    public IScheduledTask getTask(AExtension extension, UUID uuid) {
        if(Validator.isNull(extension, uuid) || !tsks.containsKey(extension.getID()) || tsks.get(extension.getID()) == null || !tsks.get(extension.getID()).containsKey(uuid) ||  tsks.get(extension.getID()).get(uuid) == null) return null;
        return tsks.get(extension.getID()).get(uuid);
    }
}