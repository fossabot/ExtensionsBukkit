package ml.extbukkit.main;


import ml.extbukkit.api.scheduler.ISchedulerManager;
import ml.extbukkit.api.scheduler.exception.RunnableException;
import ml.extbukkit.main.server.Server;
import org.bukkit.scheduler.BukkitRunnable;

public class BukkitRunner
{

    public static void start()
    {
        ISchedulerManager schedulerManager = Server.getInstance().getSchedulerManager();
        new BukkitRunnable()
        {

            @Override
            public void run()
            {
                schedulerManager.getRepeatingTasks().forEach( (extension, task) ->
                {
                    if ( task.getDelay() != 0 )
                    {
                        try
                        {
                            Thread.sleep( task.getUnit().toMillis( task.getDelay() ) );
                        } catch ( InterruptedException exc )
                        {
                            exc.printStackTrace();
                        }
                    }
                    new BukkitRunnable() {

                        @Override
                        public void run()
                        {
                            try
                            {
                                task.getRunnable().execute();
                            } catch ( Throwable t )
                            {
                                throw new RunnableException( "Internal error occurred while trying to execute task '" + task.getUUID() + "' in extension '" + extension.getName() + "'", t );
                            }
                        }
                    }.runTaskTimer( BukkitExtensionsBukkit.getInstance(), 0, task.getUnit().toMillis( task.getPeriod() ) / 50 );
                } );
            }
        }.runTaskTimer( BukkitExtensionsBukkit.getInstance(), 0, 20 );
    }

}