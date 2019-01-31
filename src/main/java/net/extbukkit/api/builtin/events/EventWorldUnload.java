package net.extbukkit.api.builtin.events;

import net.extbukkit.api.event.Preventable;
import net.extbukkit.api.world.IWorld;

public class EventWorldUnload extends EventWorld implements Preventable {
    boolean prevented;

    public EventWorldUnload(IWorld world) {
        super(world);
    }

    @Override
    public void setPrevented(boolean value) {
        prevented = value;
    }

    @Override
    public boolean isPrevented() {
        return prevented;
    }
}