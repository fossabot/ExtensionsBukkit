package ml.extbukkit.api.event;

public interface IEventManager<T extends Event> {
    void callEvent(T event);
    void registerHandler(Class<T> eventClass, IHandler<T> handler);
}