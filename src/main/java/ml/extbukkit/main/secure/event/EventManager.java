package ml.extbukkit.main.secure.event;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.event.IHandler;

import java.util.*;

public class EventManager implements IEventManager {

    private static Set<RegisteredHandler> handlers = new HashSet<>(); // !! MUST BE STATIC !!

    @Override
    public void callEvent(Event event) {
        Set<IHandler> hsh = new HashSet<>();
        Set<IHandler> hh = new HashSet<>();
        Set<IHandler> nh = new HashSet<>();
        Set<IHandler> lh = new HashSet<>();
        Set<IHandler> lsh = new HashSet<>();
        handlers.forEach(rHandler -> {
            if (rHandler.getEventClass().isAssignableFrom(event.getClass())) {
                switch (rHandler.getHandler().priority()) {
                    case HIGHEST:
                        hsh.add(rHandler.getHandler());
                        break;
                    case HIGH:
                        hh.add(rHandler.getHandler());
                        break;
                    case NORMAL:
                        nh.add(rHandler.getHandler());
                        break;
                    case LOW:
                        lh.add(rHandler.getHandler());
                        break;
                    case LOWEST:
                        lsh.add(rHandler.getHandler());
                        break;
                    default:
                        nh.add(rHandler.getHandler());
                        break;
                }
            }
        });
        hsh.forEach(h -> h.handle(event));
        hh.forEach(h -> h.handle(event));
        nh.forEach(h -> h.handle(event));
        lh.forEach(h -> h.handle(event));
        lsh.forEach(h -> h.handle(event));
        lsh.clear();
        lh.clear();
        nh.clear();
        hh.clear();
        hsh.clear();
    }

    @Override
    public void registerHandler(Class<? extends Event> clazz, IHandler handler) {
        handlers.add(new RegisteredHandler(handler, clazz));
    }

    @Override
    public void unregisterHandler(Class<? extends Event> eventClass, IHandler handler) {
        Iterator<RegisteredHandler> iterator = handlers.iterator();
        while (iterator.hasNext()) {
            RegisteredHandler reg = iterator.next();
            if (reg.getEventClass().equals(eventClass)) {
                if (reg.getHandler().equals(handler)) {
                    iterator.remove();
                }
            }
        }
    }

    @Override
    public Collection<IHandler> getHandlers(Class<? extends Event> eventClass) {
        Collection<IHandler> normalCollection = new HashSet<>();
        handlers.forEach(registeredHandler -> {
            if (registeredHandler.getEventClass().isAssignableFrom(eventClass)) {
                normalCollection.add(registeredHandler.getHandler());
            }
        });
        return Collections.unmodifiableCollection(normalCollection);
    }

}