package ml.extbukkit.api.builtin.events;

import ml.extbukkit.api.world.IWorld;
import ml.extbukkit.api.event.Event;

public class EventWorld extends Event {
    private IWorld world;

    public EventWorld(IWorld world) {
        this.world = world;
    }

    public IWorld getWorld() {
        return world;
    }
}