package net.extbukkit.api.builtin.events;

import net.extbukkit.api.event.Event;
import net.extbukkit.api.world.IWorld;

public class EventWorld extends Event {
    private IWorld world;

    public EventWorld(IWorld world) {
        this.world = world;
    }

    public IWorld getWorld() {
        return world;
    }
}