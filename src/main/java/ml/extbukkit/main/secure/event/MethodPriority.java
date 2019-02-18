package ml.extbukkit.main.secure.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.event.HandlePriority;
import ml.extbukkit.api.event.IHandlerContainer;
import ml.extbukkit.api.event.IMethodPriority;

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
            if ( event.getClass().isAssignableFrom( method.getParameterTypes()[0] ) )
            {
                method.invoke( container, event );
            }
        } catch ( IllegalAccessException | InvocationTargetException e )
        {
            e.printStackTrace();
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