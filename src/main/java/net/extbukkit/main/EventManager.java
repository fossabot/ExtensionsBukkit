package net.extbukkit.main;

import net.extbukkit.api.event.Event;
import net.extbukkit.api.event.HandlePriority;
import net.extbukkit.api.event.Handler;
import net.extbukkit.api.event.IEventManager;
import net.extbukkit.api.extension.Extension;

import java.util.ArrayList;
import java.util.List;

public class EventManager implements IEventManager {
    private List<Handler> handlers = new ArrayList<>();
    @Override
    public Event pullEvent(Event e) {
        List<Handler> fph = new ArrayList<>();
        List<Handler> afph = new ArrayList<>();
        List<Handler> nph = new ArrayList<>();
        List<Handler> anph = new ArrayList<>();
        List<Handler> lph = new ArrayList<>();
        for(Handler h : handlers)
            switch(h.getPriority()) {
                case FIRST:
                    fph.add(h);
                    break;
                case AFTERFIRST:
                    afph.add(h);
                    break;
                case NORMAL:
                    nph.add(h);
                    break;
                case AFTERNORMAL:
                    anph.add(h);
                    break;
                case LAST:
                    lph.add(h);
                    break;
                default:
                    nph.add(h);
                    break;
            }
        for(Handler h : fph)
            h.handle(e);
        for(Handler h : afph)
            h.handle(e);
        for(Handler h : nph)
            h.handle(e);
        for(Handler h : anph)
            h.handle(e);
        for(Handler h : lph)
            h.handle(e);
        return e;
    }

    @Override
    public void registerHandler(Extension extension, Handler eventHandler) {
        handlers.add(eventHandler);
    }
}