package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import ml.extbukkit.api.event.Event;
import ml.extbukkit.api.world.entity.IEntity;

public class EventEntity extends Event
{

    @Getter
    private IEntity entty;

    public EventEntity(IEntity entity)
    {
        this.entty = entity;
    }

}