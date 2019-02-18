package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import lombok.Setter;
import ml.extbukkit.api.world.entity.IEntity;

public class EventEntityJoin extends EventEntity {

    @Getter
    @Setter
    private String joinMessage;

    public EventEntityJoin(IEntity entity, String joinMessage) {
        super(entity);
        this.joinMessage = joinMessage;
    }

}