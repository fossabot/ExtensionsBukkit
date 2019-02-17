package ml.extbukkit.api.event;

public interface IEventManager {
    void callEvent(Event event);
    void registerContainer(IHandlerContainer container);
}