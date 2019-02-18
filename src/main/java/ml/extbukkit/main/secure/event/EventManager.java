package ml.extbukkit.main.secure.event;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.Handler;
import ml.extbukkit.api.event.IEventManager;
import ml.extbukkit.api.event.IHandlerContainer;
import ml.extbukkit.api.event.IMethodPriority;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class EventManager implements IEventManager {
    private Multimap<IHandlerContainer, IMethodPriority> containers = ArrayListMultimap.create();

    @Override
    public void callEvent(Event event) {
        for (IHandlerContainer container : containers.keySet()) {
            Collection<IMethodPriority> priorityMap = getEventsIn( container );
            Set<IMethodPriority> highest = new HashSet<>();
            Set<IMethodPriority> high = new HashSet<>();
            Set<IMethodPriority> normal = new HashSet<>();
            Set<IMethodPriority> low = new HashSet<>();
            Set<IMethodPriority> lowest = new HashSet<>();
            priorityMap.forEach( methodPriority ->
            {
                System.out.println("SWITCHING");
                switch ( methodPriority.getPriority() )
                {
                    case HIGHEST:
                        highest.add( methodPriority );
                        break;
                    case HIGH:
                        high.add( methodPriority );
                        break;
                    case NORMAL:
                        normal.add( methodPriority );
                        break;
                    case LOW:
                        low.add( methodPriority );
                        break;
                    case LOWEST:
                        lowest.add( methodPriority );
                        break;
                    default:
                        normal.add( methodPriority );
                }
            } );
            System.out.println("CALLING");
            highest.forEach( priority -> priority.invoke( container, event ) );
            high.forEach( priority -> priority.invoke( container, event ) );
            normal.forEach( priority -> priority.invoke( container, event ) );
            low.forEach( priority -> priority.invoke( container, event ) );
            lowest.forEach( priority -> priority.invoke( container, event ) );
            System.out.println("CALLED");
//            priorityMap.forEach( (method, priority) ->
//            {
//                switch ( priority )
//                {
//                    case HIGHEST:
//                        highest.add( method );
//                        break;
//                    case HIGH:
//                        high.add( method );
//                        break;
//                    case NORMAL:
//                        normal.add( method );
//                    case LOW:
//                        low.add( method );
//                    case LOWEST:
//                        lowest.add( method );
//                }
//            });
//            for ( Method hiImpl : highest )
//            {
//                invoke( hiImpl, event, container );
//            }
//            for ( Method hImpl : high )
//            {
//                invoke( hImpl, event, container );
//            }
//            for ( Method nImpl : normal )
//            {
//                invoke( nImpl, event, container );
//            }
//            for ( Method lImpl : low )
//            {
//                invoke( lImpl, event, container );
//            }
//            for ( Method loImpl : lowest )
//            {
//                invoke( loImpl, event, container );
//            }
//            lowest.clear();
//            low.clear();
//            normal.clear();
//            high.clear();
//            highest.clear();
        }
    }

    @Override
    public void registerContainer(IHandlerContainer container) {
        for ( Method method : container.getClass().getMethods() )
        {
            for ( Annotation annotation : method.getAnnotations() )
            {
                if ( annotation instanceof Handler )
                {
                    Handler anno = (Handler) annotation;
                    if ( method.getParameterCount() != 1 )
                    {
                        throw new IllegalArgumentException( "Cannot handle event method with more than 1 parameters!" );
                    }
                    if ( containers.containsKey( container ) )
                    {
                        throw new IllegalArgumentException( "Event container already registered" );
                    }
                    IMethodPriority priority = new MethodPriority( method, anno.priority() );
                    containers.put( container, priority );
                }
            }
        }
    }

    @Override
    public Collection<IMethodPriority> getEventsIn(IHandlerContainer container)
    {
        return Collections.unmodifiableCollection( containers.get( container ) );
    }

}