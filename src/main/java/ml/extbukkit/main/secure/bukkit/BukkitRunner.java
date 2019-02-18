package ml.extbukkit.main.secure.bukkit;

import ml.extbukkit.api.scheduler.IScheduledTask;
import ml.extbukkit.api.scheduler.TaskType;
import ml.extbukkit.main.secure.scheduler.ScheduledTask;
import ml.extbukkit.main.secure.server.Server;

import java.util.Map;
import java.util.UUID;

public class BukkitRunner implements Runnable {
    @Override
    public void run() {
        for(Map<UUID, IScheduledTask> mut : Server.getInstance().getSchedulerManager().getTasks().values())
            for(IScheduledTask t : mut.values()) {
                ScheduledTask it = (ScheduledTask) t;
                if(it.getDelayTime() > 0) {
                    it.setDelayTime(it.getDelayTime() - 1);
                    continue;
                }
                it.setTime(it.getTime() + 1);
                if(it.getTime() >= it.getInterval()) {
                    it.getTask().execute();
                    it.setTime(0);
                    if(it.getType() == TaskType.DELAYED)
                        it.cancel();
                }
            }
    }
}