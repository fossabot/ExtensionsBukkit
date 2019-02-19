package ml.extbukkit.main.secure.event;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.event.IHandler;

import java.util.*;

public class EventManager implements IEventManager {
    private static List<RegisteredHandler> rh = new ArrayList<>();

    @Override
    public void callEvent(Event event) {
        List<IHandler> hsh = new ArrayList<>();
        List<IHandler> hh = new ArrayList<>();
        List<IHandler> nh = new ArrayList<>();
        List<IHandler> lh = new ArrayList<>();
        List<IHandler> lsh = new ArrayList<>();
        for(RegisteredHandler h : rh)
            if(h.getClazz().isAssignableFrom(event.getClass()))
                switch(h.getHandler().priority()) {
                    case HIGHEST:
                        hsh.add(h.getHandler());
                        break;
                    case HIGH:
                        hh.add(h.getHandler());
                        break;
                    case NORMAL:
                        nh.add(h.getHandler());
                        break;
                    case LOW:
                        lh.add(h.getHandler());
                        break;
                    case LOWEST:
                        lsh.add(h.getHandler());
                        break;
                    default:
                        nh.add(h.getHandler());
                        break;
                }
        for(IHandler h : hsh)
            h.handle(event);
        for(IHandler h : hh)
            h.handle(event);
        for(IHandler h : nh)
            h.handle(event);
        for(IHandler h : lh)
            h.handle(event);
        for(IHandler h : lsh)
            h.handle(event);
    }

    @Override
    public void registerHandler(Class<? extends Event> clazz, IHandler handler) {
        rh.add(new RegisteredHandler(handler, clazz));
        System.out.println(rh.size());
    }
}