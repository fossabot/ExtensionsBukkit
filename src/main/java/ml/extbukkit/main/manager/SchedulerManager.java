package ml.extbukkit.main.manager;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.scheduler.ScheduledTask;
import ml.extbukkit.api.scheduler.Task;
import ml.extbukkit.api.scheduler.exception.RunnableException;
import ml.extbukkit.main.scheduler.SimpleScheduledTask;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SchedulerManager implements ISchedulerManager {
    private Multimap<AExtension, ScheduledTask> tasks = ArrayListMultimap.create();
    private Map<UUID, ScheduledTask> tasksByUUID = new HashMap<>();

    @Override
    public void scheduleDelayed(AExtension owner, Task task, long delay, TimeUnit unit)
    {
        try
        {
            Thread.sleep( unit.toMillis( delay ) );
        } catch ( InterruptedException exc )
        {
            exc.printStackTrace();
        }
        try
        {
            task.execute();
        } catch ( Throwable t )
        {
            throw new RunnableException( "An internal error occured while running task '" + task.toString() + "' in extension '" + owner.getName() + "'", t );
        }
    }

    @Override
    public ScheduledTask scheduleRepeating(AExtension owner, Task task, long delay, long period, TimeUnit unit)
    {
        ScheduledTask taskScheduled = new SimpleScheduledTask( delay, period, unit, owner, task );
        tasks.put( owner, taskScheduled );
        tasksByUUID.put( taskScheduled.getUUID(), taskScheduled );
        return taskScheduled;
    }

    @Override
    public void cancelAll(AExtension extension)
    {
        tasks.get( extension ).forEach( ScheduledTask::cancel );
        tasks.get( extension ).clear(); // get rid of some memory
    }

    @Override
    public void cancel(UUID uuid)
    {
        tasksByUUID.remove( uuid );
        tasks.values().remove( getRepeatingTask( uuid ) );
    }

    @Override
    public Multimap<AExtension, ScheduledTask> getRepeatingTasks()
    {
        return tasks;
    }

    @Override
    public ScheduledTask getRepeatingTask(UUID uuid)
    {
        return tasksByUUID.get( uuid );
    }

}