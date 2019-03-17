package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import lombok.Setter;
import ml.extbukkit.api.world.entity.IEntity;

public class EventPlayerJoin extends EventPlayer
{

    @Getter
    @Setter
    private String joinMessage;

    public EventPlayerJoin(IEntity entity, String joinMessage) {
        super(entity);
        this.joinMessage = joinMessage;
    }

}