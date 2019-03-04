package ml.extbukkit.main.secure.scheduler;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.scheduler.IScheduledTask;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.main.server.Server;

@EqualsAndHashCode
public class ScheduledTask implements IScheduledTask
{

    private long delay, interval;
    private AExtension owner;
    private ISchedulerManager schedulerManager = Server.getInstance().getSchedulerManager();
    private Runnable task;
    private UUID uuid;

    public ScheduledTask(long delay, long interval, AExtension owner, Runnable task )
    {
        this.uuid = UUID.randomUUID();
        this.delay = delay;
        this.interval = interval;
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
    public long getInterval()
    {
        return interval;
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
    public Runnable getTask()
    {
        return task;
    }

}