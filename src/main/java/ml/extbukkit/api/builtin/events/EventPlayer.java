package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.world.entity.IEntity;

public class EventPlayer extends Event {
    @Getter
    private IEntity player;

    public EventPlayer(IEntity player) {
        this.player = player;
    }

}