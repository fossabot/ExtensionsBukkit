package net.extbukkit.api.scheduler;

public interface Task {
    void execute();
    long getDelay();
    TaskType getType();
}