package net.extbukkit.main;

import net.extbukkit.api.scheduler.Task;
import net.extbukkit.api.scheduler.TaskType;
import net.extbukkit.main.server.Server;

import java.util.UUID;

public class BukkitRunner implements Runnable {
    private long num = 0;
    @Override
    public void run() {
        for(UUID u : Server.getInstance().getSchedulerManager().getTasks().keySet()) {
            Task t = Server.getInstance().getSchedulerManager().getTasks().get(u);
            if(num % t.getDelay() == 0) {
                t.execute();
                if(t.getType() == TaskType.ONETIME)
                    Server.getInstance().getSchedulerManager().stop(u);
            }
        }
        if(num < Long.MAX_VALUE)
            num++;
        else num = 0;
    }
}