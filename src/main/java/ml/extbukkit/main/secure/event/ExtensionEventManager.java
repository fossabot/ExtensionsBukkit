package ml.extbukkit.main.secure.event;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.EventHandler;
import ml.extbukkit.api.event.EventManager;
import ml.extbukkit.api.event.HandlePriority;

public class ExtensionEventManager implements EventManager {

  private static Map<HandlePriority, Map<Class<? extends Event>, EventHandler<? extends Event>>> byPriority = new LinkedHashMap<>(); // !! MUST BE STATIC !!

  @Override
  public <T extends Event> T callEvent(T event) {
    Map<HandlePriority, Map<Class<? extends Event>, EventHandler<? extends Event>>> sortedMap
      = byPriority.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey))
      .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e1, LinkedHashMap::new));
    sortedMap.forEach((priority, handlers) -> {
      EventHandler handler = handlers.get(event.getClass());
      if(handler == null) {
        return; // no handlers for event, skip
      }
      handler.handle(event);
    });
    return event;
  }

  @Override
  public <T extends Event> void registerHandler(Class<T> clazz, EventHandler<T> handler) {
    Map<Class<? extends Event>, EventHandler<? extends Event>> handlersMap = byPriority.get(handler.priority());
    if(handlersMap == null) {
      handlersMap = new HashMap<>();
    }
    handlersMap.put(clazz, handler);
    byPriority.put(handler.priority(), handlersMap);
  }

  @Override
  public Collection<EventHandler<? extends Event>> getHandlers(Class<? extends Event> eventClass) {
    Collection<EventHandler<? extends Event>> normalCollection = new HashSet<>();
    byPriority.forEach((priority, handlers) -> {
      EventHandler<? extends Event> handler = handlers.get(eventClass);
      normalCollection.add(handler);
    });
    return Collections.unmodifiableCollection(normalCollection);
  }

}