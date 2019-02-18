package ml.extbukkit.main.secure.scheduler;

import java.util.UUID;

import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.scheduler.*;
import ml.extbukkit.main.secure.server.Server;

public class ScheduledTask implements IScheduledTask {
    private long delay, interval, time = 0;
    private TimeUnit unitDelay, unitInterval;
    private AExtension owner;
    private ISchedulerManager schedulerManager = Server.getInstance().getSchedulerManager();
    private ITask task;
    private UUID uuid;
    private TaskType type;

    public ScheduledTask(long delay, long interval, TimeUnit unitDelay, TimeUnit unitInterval, AExtension owner, ITask task, UUID uuid, TaskType type) {
        this.uuid = uuid;
        this.delay = delay;
        this.interval = interval;
        this.unitDelay = unitDelay;
        this.unitInterval = unitInterval;
        this.owner = owner;
        this.task = task;
        this.type = type;
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
    public TimeUnit getDelayUnit() {
        return unitDelay;
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

    @Override
    public TimeUnit getIntervalUnit() {
        return unitInterval;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime() {
        return this.time;
    }

    public long getRealInterval() {
        return unitInterval.multiply(getInterval());
    }

    public long getRealDelay() {
        return unitDelay.multiply(getDelay());
    }
}