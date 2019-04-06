package ml.extbukkit.main.secure.scheduler;

import java.util.UUID;
import java.util.function.Consumer;

import lombok.EqualsAndHashCode;
import ml.extbukkit.api.extension.Extension;
import ml.extbukkit.api.scheduler.ScheduledTask;
import ml.extbukkit.api.scheduler.SchedulerManager;
import ml.extbukkit.api.server.Server;

@EqualsAndHashCode
public class ExtensionedScheduledTask implements ScheduledTask {

  private long delay, interval;
  private Extension owner;
  private SchedulerManager schedulerManager = Server.getInstance().getSchedulerManager();
  private Runnable task;
  private UUID uuid;

  public ExtensionedScheduledTask(long delay, long interval, Extension owner, Runnable task) {
    this.uuid = UUID.randomUUID();
    this.delay = delay;
    this.interval = interval;
    this.owner = owner;
    this.task = task;
  }

  public ExtensionedScheduledTask(long delay, long interval, Extension owner, Consumer<ml.extbukkit.api.scheduler.ScheduledTask> callback) {
    this.uuid = UUID.randomUUID();
    this.delay = delay;
    this.interval = interval;
    this.owner = owner;
    this.task = () -> callback.accept(this);
  }

  @Override
  public void cancel() {
    schedulerManager.cancel(uuid);
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
  public UUID getUUID() {
    return uuid;
  }

  @Override
  public Extension getOwner() {
    return owner;
  }

  @Override
  public Runnable getTask() {
    return task;
  }

}