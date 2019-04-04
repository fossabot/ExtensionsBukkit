package ml.extbukkit.main.secure.scheduler;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import ml.extbukkit.api.extension.Extension;
import ml.extbukkit.api.scheduler.ScheduledTask;
import ml.extbukkit.api.server.Server;
import ml.extbukkit.api.util.Time;
import ml.extbukkit.main.secure.bukkit.BukkitExtensionsBukkit;

public class SimpleScheduler implements ml.extbukkit.api.scheduler.SchedulerManager {

  private Map<UUID, ExtensionedScheduledTask> tasks = new HashMap<>();
  private Multimap<Extension, ExtensionedScheduledTask> tasksByExtension = ArrayListMultimap.create();
  private BukkitExtensionsBukkit plugin = BukkitExtensionsBukkit.getInstance();

  private static SimpleScheduler instance = new SimpleScheduler();

  public static SimpleScheduler getInstance() {
    return instance;
  }

  private SimpleScheduler() {
  }

  @Override
  public ScheduledTask schedule(Extension owner, Runnable task, Time delay, Time interval) {
    ExtensionedScheduledTask taskScheduled = new ExtensionedScheduledTask(delay.toLong(), interval.toLong(), owner, task);
    tasks.put(taskScheduled.getUUID(), taskScheduled);
    tasksByExtension.put(owner, taskScheduled);
    return taskScheduled;
  }

  @Override
  public void schedule(Extension owner, Consumer<ScheduledTask> callbackTask, Time delay, Time interval) {
    ExtensionedScheduledTask taskScheduled = new ExtensionedScheduledTask(delay.toLong(), interval.toLong(), owner, callbackTask);
    tasks.put(taskScheduled.getUUID(), taskScheduled);
    tasksByExtension.put(owner, taskScheduled);
  }

  @Override
  public void schedule(Extension owner, Runnable task, Time delay) {
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
  public void cancelAll(Extension extension) {
    tasksByExtension.get(extension).forEach(task -> {
      for(Map.Entry<UUID, ExtensionedScheduledTask> taskEntry : tasks.entrySet()) {
        if(taskEntry.getValue().equals(task)) {
          tasks.remove(taskEntry.getKey());
        }
      }
    });
    tasksByExtension.get(extension).clear();
  }

  @Override
  public void cancel(UUID uuid) {
    tasks.remove(uuid);
  }

  @Override
  public ScheduledTask getTask(UUID uuid) {
    return tasks.get(uuid);
  }

  public Map<UUID, ExtensionedScheduledTask> getTasks() {
    return tasks;
  }

}