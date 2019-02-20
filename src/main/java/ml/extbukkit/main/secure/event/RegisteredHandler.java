package ml.extbukkit.main.secure.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.IHandler;

@AllArgsConstructor
public class RegisteredHandler {
    @Getter
    private IHandler handler;

    @Getter
    private Class<? extends Event> eventClass;
}