package ml.extbukkit.api.scheduler;

import java.util.UUID;

import ml.extbukkit.api.extension.AExtension;

public interface IScheduledTask {
    void cancel();
    long getDelay();
    long getInterval();
    UUID getUUID();
    AExtension getOwner();
    ITask getTask();
    TaskType getType();
}