package ml.extbukkit.main.manager;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.HandlePriority;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.event.IHandler;

import java.util.*;

public class EventManager<T extends Event> implements IEventManager<T>
{
    private ListMultimap<Class<? extends Event>, IHandler<T>> registeredHandlers =
            Multimaps.synchronizedListMultimap( Multimaps.newListMultimap( new IdentityHashMap<>(), ArrayList::new ) );

    @Override
    public void callEvent(T event)
    {
        if ( event == null )
        {
            throw new NullPointerException( "The system cannot call a null event" );
        }
        List<IHandler<T>> handlers = registeredHandlers.get( event.getClass() );
        Set<IHandler<T>> lowest = new HashSet<>();
        Set<IHandler<T>> low = new HashSet<>();
        Set<IHandler<T>> normal = new HashSet<>();
        Set<IHandler<T>> high = new HashSet<>();
        Set<IHandler<T>> highest = new HashSet<>();
        handlers.forEach( handler ->
        {
            if ( handler == null )
            {
                return;
            }
            HandlePriority priority = handler.priority();
            switch ( priority )
            {
                case HIGHEST:
                    highest.add( handler );
                    break;
                case HIGH:
                    high.add( handler );
                    break;
                case NORMAL:
                    normal.add( handler );
                    break;
                case LOW:
                    low.add( handler );
                    break;
                case LOWEST:
                    lowest.add( handler );
                    break;
                default:
                    normal.add( handler );
            }
        } );
        for ( IHandler<T> hHig : highest )
        {
            hHig.handle( event );
        }
        for ( IHandler<T> hHi : high )
        {
            hHi.handle( event );
        }
        for ( IHandler<T> norm : normal )
        {
            norm.handle( event );
        }
        for ( IHandler<T> lowH : low )
        {
            lowH.handle( event );
        }
        for ( IHandler<T> lowestH : lowest )
        {
            lowestH.handle( event );
        }
        lowest.clear();
        low.clear();
        normal.clear();
        high.clear();
        highest.clear();
        event.postCall();
    }

    @Override
    public void registerHandler(Class<T> eventClass, IHandler<T> handler)
    {
        registeredHandlers.put( eventClass, handler );
    }
}