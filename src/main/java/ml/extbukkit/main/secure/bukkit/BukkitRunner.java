package ml.extbukkit.main.secure.bukkit;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import ml.extbukkit.api.server.Server;
import ml.extbukkit.main.secure.scheduler.ExtensionedScheduledTask;
import ml.extbukkit.main.secure.scheduler.SimpleScheduler;
import org.bukkit.scheduler.BukkitRunnable;

public class BukkitRunner implements Runnable {

    // don't repeatly get these, can reduce memory leaks
    private SimpleScheduler manager = SimpleScheduler.getInstance();
    private BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();
    private Set<ExtensionedScheduledTask> scheduled = new HashSet<>();

    @Override
    public void run() {
        for (Map.Entry<UUID, ExtensionedScheduledTask> taskEntry : manager.getTasks().entrySet()) {
            ExtensionedScheduledTask task = taskEntry.getValue();
            if (scheduled.contains(task)) {
                return;
            }
            new BukkitRunnable() {

                @Override
                public void run() {
                    scheduled.add(task);
                    if (!manager.getTasks().containsKey(task.getUUID())) {
                        cancel();
                        scheduled.remove(task);
                        return;
                    }
                    try {
                        task.getTask().run();
                    } catch (Throwable t) {
                        Server.getInstance().getGlobalLogger().logStack("Internal error occured trying to execute task " +
                                "'" + task.getUUID() + "' in extension '" + task.getOwner().getName() + "'", t);
                    }
                }
            }.runTaskTimer(plugin, task.getDelay(), task.getInterval());
        }
    }
}