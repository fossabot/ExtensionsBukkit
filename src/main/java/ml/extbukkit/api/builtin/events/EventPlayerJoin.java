package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import lombok.Setter;
import ml.extbukkit.api.connection.ExtensionedPlayer;

public class EventPlayerJoin extends EventPlayer {

    @Getter
    @Setter
    private String joinMessage;

    public EventPlayerJoin(ExtensionedPlayer entity, String joinMessage) {
        super(entity);
        this.joinMessage = joinMessage;
    }

}