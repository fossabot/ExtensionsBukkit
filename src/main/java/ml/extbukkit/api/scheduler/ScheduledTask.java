package ml.extbukkit.api.scheduler;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import ml.extbukkit.api.extension.AExtension;

public interface ScheduledTask {

    void cancel();

    long getDelay();

    long getPeriod();

    TimeUnit getUnit();

    UUID getUUID();

    AExtension getOwner();

    Task getRunnable();

}