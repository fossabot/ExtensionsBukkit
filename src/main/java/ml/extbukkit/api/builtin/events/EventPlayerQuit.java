package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import lombok.Setter;
import ml.extbukkit.api.world.entity.IEntity;

public class EventPlayerQuit extends EventPlayer {
    @Getter
    @Setter
    private String quitMessage;

    public EventPlayerQuit(IEntity entity, String quitMessage) {
        super(entity);
        this.quitMessage = quitMessage;
    }

}
