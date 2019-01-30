package net.extbukkit.api.event;

@FunctionalInterface
public interface Handler {
    void handle(Event e);
    default HandlePriority getPriority() {
        return HandlePriority.NORMAL;
    };
}