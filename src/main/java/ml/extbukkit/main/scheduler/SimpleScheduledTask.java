package ml.extbukkit.main.scheduler;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.scheduler.ScheduledTask;
import ml.extbukkit.api.scheduler.Task;
import ml.extbukkit.main.server.Server;

public class SimpleScheduledTask implements ScheduledTask
{
    private UUID uuid;
    private long delay;
    private long period;
    private TimeUnit unit;
    private AExtension owner;
    private ISchedulerManager schedulerManager = Server.getInstance().getSchedulerManager();
    private Task task;

    public SimpleScheduledTask(long delay, long period, TimeUnit unit, AExtension owner, Task task)
    {
        this.uuid = UUID.randomUUID();
        this.delay = delay;
        this.period = period;
        this.unit = unit;
        this.owner = owner;
        this.task = task;
    }

    @Override
    public void cancel()
    {
        schedulerManager.cancel( uuid );
    }

    @Override
    public long getDelay()
    {
        return delay;
    }

    @Override
    public long getPeriod()
    {
        return period;
    }

    @Override
    public TimeUnit getUnit()
    {
        return unit;
    }

    @Override
    public UUID getUUID()
    {
        return uuid;
    }

    @Override
    public AExtension getOwner()
    {
        return owner;
    }

    @Override
    public Task getRunnable()
    {
        return task;
    }

}