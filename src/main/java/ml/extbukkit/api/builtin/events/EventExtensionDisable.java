package ml.extbukkit.api.builtin.events;

import ml.extbukkit.api.extension.AExtension;

/**
 * Event, called when extension is disabled
 */
public class EventExtensionDisable extends EventExtension
{

    public EventExtensionDisable(AExtension extension)
    {
        super( extension );
    }

}