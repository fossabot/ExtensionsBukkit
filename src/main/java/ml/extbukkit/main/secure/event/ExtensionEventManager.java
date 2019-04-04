package ml.extbukkit.main.secure.event;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.EventHandler;
import ml.extbukkit.api.event.EventManager;

public class ExtensionEventManager implements EventManager {

  private static Set<RegisteredHandler> handlers = new HashSet<>(); // !! MUST BE STATIC !!

  @Override
  public <T extends Event> T callEvent(T event) {
    Set<EventHandler> hsh = new HashSet<>();
    Set<EventHandler> hh = new HashSet<>();
    Set<EventHandler> nh = new HashSet<>();
    Set<EventHandler> lh = new HashSet<>();
    Set<EventHandler> lsh = new HashSet<>();
    handlers.forEach(rHandler -> {
      if(rHandler.getEventClass().isAssignableFrom(event.getClass())) {
        switch(rHandler.getHandler().priority()) {
          case HIGHEST:
            hsh.add(rHandler.getHandler());
            break;
          case HIGH:
            hh.add(rHandler.getHandler());
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
    return event;
  }

  @Override
  public <T extends Event> void registerHandler(Class<T> clazz, EventHandler<T> handler) {
    handlers.add(new RegisteredHandler(handler, clazz));
  }

  @Override
  public Collection<EventHandler> getHandlers(Class<? extends Event> eventClass) {
    Collection<EventHandler> normalCollection = new HashSet<>();
    handlers.forEach(registeredHandler -> {
      if(registeredHandler.getEventClass().isAssignableFrom(eventClass)) {
        normalCollection.add(registeredHandler.getHandler());
      }
    });
    return Collections.unmodifiableCollection(normalCollection);
  }

}