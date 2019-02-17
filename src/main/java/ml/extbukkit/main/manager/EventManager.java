package ml.extbukkit.main.manager;

import com.google.common.collect.ArrayListMultimap;
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
import java.util.IdentityHashMap;

public class EventManager implements IEventManager {
    private ListMultimap<IHandlerContainer, Method> mtdl = Multimaps.synchronizedListMultimap(Multimaps.newListMultimap(new IdentityHashMap<>(), ArrayList::new));

    @Override
    public void callEvent(Event event) {
        System.out.println("CALLEVENT!");
        for (IHandlerContainer c : mtdl.keySet()) {
            for (Method m : mtdl.get(c)) {
                if (event.getClass().isAssignableFrom(m.getParameterTypes()[0])) {
                    m.setAccessible(true);
                    try {
                        m.invoke(c, event);
                        System.out.println("INVOKE!");
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void registerContainer(IHandlerContainer container) {
        System.out.println("Registering container");
        registerContainerMethods(container);
    }

    public void registerContainerMethods(IHandlerContainer c) {
        Class<? extends IHandlerContainer> hc = c.getClass();
        Method[] mtd = hc.getMethods();
        for (Method m : mtd) {
            Annotation a = m.getAnnotation(Handler.class);
            if (a == null)
                continue;
            if (m.getParameterCount() == 1 && m.getReturnType() == void.class && Event.class.isAssignableFrom(m.getParameterTypes()[0]))
                mtdl.put(c, m);
            System.out.println(m.getName());
        }
    }
}