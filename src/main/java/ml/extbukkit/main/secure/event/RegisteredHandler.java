package ml.extbukkit.main.secure.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.IHandler;

@AllArgsConstructor
@Getter
public class RegisteredHandler {

    private IHandler handler;

    private Class<? extends Event> eventClass;
}