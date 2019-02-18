package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import lombok.Setter;
import ml.extbukkit.api.world.entity.IEntity;

public class EventEntityQuit extends EventEntity {

    @Getter
    @Setter
    private String quitMessage;

    public EventEntityQuit(IEntity entity, String quitMessage) {
        super(entity);
        this.quitMessage = quitMessage;
    }

}
