package ml.extbukkit.main.secure.scheduler;

import java.util.UUID;

import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.scheduler.*;
import ml.extbukkit.main.server.Server;

public class ScheduledTask implements IScheduledTask {
    private long delay, interval, time, delayTime;
    private AExtension owner;
    private ISchedulerManager schedulerManager = Server.getInstance().getSchedulerManager();
    private ITask task;
    private UUID uuid;
    private TaskType type;

    public ScheduledTask(long delay, long interval, AExtension owner, ITask task, UUID uuid, TaskType type) {
        this.uuid = uuid;
        this.delay = delay;
        this.interval = interval;
        this.owner = owner;
        this.task = task;
        this.type = type;
        this.time = 0;
        this.delayTime = delay;
    }

    @Override
    public void cancel() {
        schedulerManager.cancel(owner, uuid);
    }

    @Override
    public long getDelay() {
        return delay;
    }

    @Override
    public long getInterval() {
        return interval;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public AExtension getOwner() {
        return owner;
    }

    @Override
    public ITask getTask() {
        return task;
    }

    @Override
    public TaskType getType() {
        return type;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return this.time;
    }

    public void setDelayTime(long time) {
        delayTime = time;
    }

    public long getDelayTime() {
        return delayTime;
    }
}