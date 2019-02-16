package ml.extbukkit.api.event;

public interface IEventManager<T extends Event>
{
    void callEvent(T event);
    void registerEventHandler(Class<T> eventClass, ISpecificHandler<T> handler);
}