package ml.extbukkit.api.event;

import java.lang.reflect.Method;

//TODO Javadoc
public interface IMethodPriority {

    void invoke(IHandlerContainer container, Event event);

    Method getMethod();

    HandlePriority getPriority();

}