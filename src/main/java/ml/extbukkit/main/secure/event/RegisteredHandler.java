package ml.extbukkit.main.secure.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.IHandler;

@Data
@AllArgsConstructor
public class RegisteredHandler {
    private IHandler handler;
    private Class<? extends Event> clazz;
}