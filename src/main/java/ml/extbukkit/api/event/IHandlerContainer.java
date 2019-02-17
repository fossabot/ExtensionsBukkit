package ml.extbukkit.api.event;

public interface IHandlerContainer {
    default HandlePriority getPriority() {
        return HandlePriority.NORMAL;
    }
}