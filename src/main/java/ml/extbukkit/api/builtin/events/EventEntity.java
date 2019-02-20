package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.world.entity.IEntity;

public class EventEntity extends Event {

    @Getter
    private IEntity entity;

    public EventEntity(IEntity entity) {
        this.entity = entity;
    }

}