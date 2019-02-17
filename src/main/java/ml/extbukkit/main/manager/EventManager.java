package ml.extbukkit.main.manager;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.Handler;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.event.IHandlerContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Set;

public class EventManager implements IEventManager {
    private ListMultimap<IHandlerContainer, Method> mtdl = Multimaps.synchronizedListMultimap(Multimaps.newListMultimap(new IdentityHashMap<>(), ArrayList::new));

    @Override
    public void callEvent(Event event) {
        for (IHandlerContainer c : mtdl.keySet()) {
            for (Method m : mtdl.get(c)) {
                if (event.getClass().isAssignableFrom(m.getParameterTypes()[0])) {
                    m.setAccessible(true);
                    try {
                        m.invoke(c, event);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void registerContainer(IHandlerContainer container) {
        registerContainerMethods(container);
    }

    public void registerContainerMethods(IHandlerContainer c) {

    }
}