package ml.extbukkit.api.builtin.events;

import lombok.Getter;
import ml.extbukkit.api.connection.ExtensionedPlayer;
import ml.extbukkit.api.event.Event;

public class EventPlayer extends Event {

    @Getter
    private ExtensionedPlayer player;

    public EventPlayer(ExtensionedPlayer player) {
        this.player = player;
    }

}