package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import ml.extbukkit.api.world.IWorld;
import ml.extbukkit.api.event.Event;

public class EventWorld extends Event {
    @Getter
    private IWorld world;

    public EventWorld(IWorld world) {
        this.world = world;
    }
}