package ml.extbukkit.main.secure.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.HandlePriority;
import ml.extbukkit.api.event.IHandlerContainer;
import ml.extbukkit.api.event.IMethodPriority;
import ml.extbukkit.api.event.exception.EventException;

public class MethodPriority implements IMethodPriority
{

    private Method method;

    private HandlePriority priority;

    public MethodPriority(Method method, HandlePriority priority)
    {
        this.method = method;
        this.priority = priority;
    }

    @Override
    public void invoke(IHandlerContainer container, Event event)
    {
        try
        {
            method.invoke( container, event );
        } catch ( IllegalAccessException | InvocationTargetException e )
        {
            throw new EventException( "Internal error occured trying to execute event '" + event.getClass().getSimpleName() + "'", e );
        }
    }

    @Override
    public Method getMethod()
    {
        return method;
    }

    @Override
    public HandlePriority getPriority()
    {
        return priority;
    }

}