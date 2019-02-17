package ml.extbukkit.main.manager;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.HandlePriority;
import ml.extbukkit.api.event.Handler;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.event.IHandlerContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EventManager implements IEventManager {
    private Map<IHandlerContainer, Map<Method, HandlePriority>> containers = new HashMap<>();

    @Override
    public void callEvent(Event event) {
        for (IHandlerContainer container : containers.keySet()) {
            Map<Method, HandlePriority> priorityMap = containers.get(container);
            Set<Method> highest = new HashSet<>();
            Set<Method> high = new HashSet<>();
            Set<Method> normal = new HashSet<>();
            Set<Method> low = new HashSet<>();
            Set<Method> lowest = new HashSet<>();
            priorityMap.forEach((method, priority) ->
            {
                switch (priority) {
                    case HIGHEST:
                        highest.add(method);
                        break;
                    case HIGH:
                        high.add(method);
                        break;
                    case NORMAL:
                        normal.add(method);
                    case LOW:
                        low.add(method);
                    case LOWEST:
                        lowest.add(method);
                }
            });
            for (Method hiImpl : highest) {
                invoke(hiImpl, event, container);
            }
            for (Method hImpl : high) {
                invoke(hImpl, event, container);
            }
            for (Method nImpl : normal) {
                invoke(nImpl, event, container);
            }
            for (Method lImpl : low) {
                invoke(lImpl, event, container);
            }
            for (Method loImpl : lowest) {
                invoke(loImpl, event, container);
            }
            lowest.clear();
            low.clear();
            normal.clear();
            high.clear();
            highest.clear();
        }
    }

    @Override
    public void registerContainer(IHandlerContainer container) {
        for (Method method : container.getClass().getMethods()) {
            for (Annotation annotation : method.getAnnotations()) {
                if (annotation instanceof Handler) {
                    Handler anno = (Handler) annotation;
                    if (method.getParameterCount() != 1) {
                        throw new IllegalArgumentException("Cannot handle event method with more than 1 parameters!");
                    }
                    if (containers.containsKey(container)) {
                        throw new IllegalArgumentException("Event container already registered");
                    }
                    Map<Method, HandlePriority> priorityMap = new HashMap<>();
                    priorityMap.put(method, anno.priority());
                    containers.put(container, priorityMap);
                }
            }
        }
    }

    private void invoke(Method method, Event event, IHandlerContainer container) {
        if (event.getClass().isAssignableFrom(method.getParameterTypes()[0])) {
            try {
                method.setAccessible(true);
                method.invoke(container, event);
            } catch (IllegalAccessException | InvocationTargetException exc) {
                exc.printStackTrace();
            }
        }
    }
}