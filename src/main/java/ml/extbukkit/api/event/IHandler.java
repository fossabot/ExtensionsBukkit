package ml.extbukkit.api.event;

@FunctionalInterface
public interface IHandler<T extends Event> {
    void handle(T event);
    default HandlePriority priority() {
        return HandlePriority.NORMAL;
    }
}