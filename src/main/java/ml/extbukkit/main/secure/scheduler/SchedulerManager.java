package ml.extbukkit.main.secure.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.scheduler.IScheduledTask;
import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.util.Time;
import ml.extbukkit.main.secure.bukkit.BukkitExtensionsBukkit;
import ml.extbukkit.main.server.Server;

public class SchedulerManager implements ISchedulerManager {

    private Map<UUID, ScheduledTask> tasks = new HashMap<>();
    private Multimap<AExtension, ScheduledTask> tasksByExtension = ArrayListMultimap.create();
    private BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();

    private static SchedulerManager instance = new SchedulerManager();

    public static SchedulerManager getInstance()
    {
        return instance;
    }

    private SchedulerManager()
    {
    }

    @Override
    public IScheduledTask schedule(AExtension owner, Runnable task, Time delay, Time interval)
    {
        ScheduledTask taskScheduled = new ScheduledTask( delay.toLong(), interval.toLong(), owner, task );
        tasks.put( taskScheduled.getUUID(), taskScheduled );
        tasksByExtension.put( owner, taskScheduled );
        return taskScheduled;
    }

    @Override
    public void schedule(AExtension owner, Consumer<IScheduledTask> callbackTask, Time delay, Time interval) {
        ScheduledTask taskScheduled = new ScheduledTask(delay.toLong(), interval.toLong(), owner, callbackTask);
        tasks.put(taskScheduled.getUUID(), taskScheduled);
        tasksByExtension.put(owner, taskScheduled);
    }

    @Override
    public void schedule(AExtension owner, Runnable task, Time delay) {
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            try {
                task.run();
            } catch(Throwable t) {
                Server.getInstance().getGlobalLogger().logStack("Internal error occured trying to execute delayed task in extension " +
                  "'" + owner.getName() + "'", t);
            }
        }, delay.toLong());
    }

    @Override
    public void cancelAll(AExtension extension)
    {
        tasksByExtension.get( extension ).forEach( task -> {
            for ( Map.Entry<UUID, ScheduledTask> taskEntry : tasks.entrySet() )
            {
                if ( taskEntry.getValue().equals( task ) )
                {
                    tasks.remove( taskEntry.getKey() );
                }
            }
        } );
        tasksByExtension.get( extension ).clear();
    }

    @Override
    public void cancel(UUID uuid)
    {
        tasks.remove( uuid );
    }

    @Override
    public IScheduledTask getTask(UUID uuid)
    {
        return tasks.get( uuid );
    }

    public Map<UUID, ScheduledTask> getTasks()
    {
        return tasks;
    }

}