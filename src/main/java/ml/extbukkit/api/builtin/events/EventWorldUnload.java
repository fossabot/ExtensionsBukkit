package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import lombok.Setter;
import ml.extbukkit.api.event.Preventable;
import ml.extbukkit.api.world.World;

public class EventWorldUnload extends EventWorld implements Preventable {

    @Getter
    @Setter
    private boolean prevented;

    public EventWorldUnload(World world) {
        super(world);
    }
}