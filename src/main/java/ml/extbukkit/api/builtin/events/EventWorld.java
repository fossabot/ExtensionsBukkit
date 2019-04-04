package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.world.World;

public class EventWorld extends Event {

    @Getter
    private World world;

    public EventWorld(World world) {
        this.world = world;
    }
}