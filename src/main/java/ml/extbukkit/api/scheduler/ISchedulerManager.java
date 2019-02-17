package ml.extbukkit.api.scheduler;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Multimap;
import ml.extbukkit.api.extension.AExtension;

public interface ISchedulerManager {
    void scheduleDelayed(AExtension owner, Task task, long delay, TimeUnit unit);
    ScheduledTask scheduleRepeating(AExtension owner, Task task, long delay, long period, TimeUnit unit);
    void cancelAll(AExtension extension);
    void cancel(UUID uuid);
    Multimap<AExtension, ScheduledTask> getRepeatingTasks(); // delayed tasks aren't saved
    ScheduledTask getRepeatingTask(UUID uuid);
}