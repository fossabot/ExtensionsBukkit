package ml.extbukkit.main.manager;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.Multimaps;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.HandlePriority;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.event.ISpecificHandler;

import java.util.*;

public class EventManager<T extends Event> implements IEventManager<T>
{
    private ListMultimap<Class<? extends Event>, ISpecificHandler<T>> registeredHandlers =
            Multimaps.synchronizedListMultimap( Multimaps.newListMultimap( new IdentityHashMap<>(), ArrayList::new ) );

    @Override
    public void callEvent(T event)
    {
        if ( event == null )
        {
            throw new NullPointerException( "The system cannot call a null event" );
        }
        List<ISpecificHandler<T>> handlers = registeredHandlers.get( event.getClass() );
        Set<ISpecificHandler<T>> lowest = new HashSet<>();
        Set<ISpecificHandler<T>> low = new HashSet<>();
        Set<ISpecificHandler<T>> normal = new HashSet<>();
        Set<ISpecificHandler<T>> high = new HashSet<>();
        Set<ISpecificHandler<T>> highest = new HashSet<>();
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
        for ( ISpecificHandler<T> hHig : highest )
        {
            hHig.handle( event );
        }
        for ( ISpecificHandler<T> hHi : high )
        {
            hHi.handle( event );
        }
        for ( ISpecificHandler<T> norm : normal )
        {
            norm.handle( event );
        }
        for ( ISpecificHandler<T> lowH : low )
        {
            lowH.handle( event );
        }
        for ( ISpecificHandler<T> lowestH : lowest )
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
    public void registerEventHandler(Class<T> eventClass, ISpecificHandler<T> handler)
    {
        registeredHandlers.put( eventClass, handler );
    }
}