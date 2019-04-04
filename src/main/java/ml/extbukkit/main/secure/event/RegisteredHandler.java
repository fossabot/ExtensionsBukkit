package ml.extbukkit.main.secure.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.EventHandler;

@AllArgsConstructor
@Getter
public class RegisteredHandler {

    private EventHandler<? extends Event> handler;

    private Class<? extends Event> eventClass;
}