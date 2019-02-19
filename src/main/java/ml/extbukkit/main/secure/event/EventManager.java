package ml.extbukkit.main.secure.event;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.event.IHandler;

import java.util.*;

public class EventManager implements IEventManager {
    Map<IHandler, Class<? extends Event>> handlers = new HashMap<>();

    @Override
    public void callEvent(Event event) {
        for(IHandler h : handlers.keySet()) {
            Class<? extends Event> eclass = handlers.get(h);
            if(eclass.isAssignableFrom(event.getClass()))
                h.handle(event);
        }
    }

    @Override
    public void registerHandler(Class<? extends Event> clazz, IHandler handler) {
        handlers.put(handler, clazz);
    }
}