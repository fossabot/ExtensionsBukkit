/*
 * Copyright 2019 Ivan Pekov (MrIvanPlays)

 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in the
 * Software without restriction, including without limitation the rights to use, copy,
 * modify, merge, publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in all copies
 * or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
 * OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 **/
package ml.extbukkit.api.cooldowns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ml.extbukkit.api.connection.ExtensionedPlayer;
import ml.extbukkit.api.extension.Extension;
import ml.extbukkit.api.scheduler.SchedulerManager;
import ml.extbukkit.api.server.Server;
import ml.extbukkit.api.util.Time;
import ml.extbukkit.api.util.TimeUnit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Represents a cooldown manager for adding a cooldowns to a player
 */
@RequiredArgsConstructor
@Getter
public final class CooldownManager {

    private final Extension extension;
    /**
     * Should the cooldown of a player be removed when he exits?
     */
    private final boolean removeOnExit;
    /**
     * All cooldowns
     */
    private Map<UUID, Map<String, Long>> cooldowns = new HashMap<>();

    /**
     * Adds a cooldown to the specified player. The time left to expire
     * is gettable via {@link #getCooldownLeft(ExtensionedPlayer, String)}
     *
     * @param player     player
     * @param time       time in seconds
     * @param identifier identifier
     */
    public void addCooldown(ExtensionedPlayer player, Time time, String identifier) {
        Map<String, Long> identifierMap = cooldowns.get(player.getUUID());
        if (identifierMap == null) {
            identifierMap = new HashMap<>();
        }
        identifierMap.put(identifier, time.getTicks() / 20);
        cooldowns.put(player.getUUID(), identifierMap);
    }

    /**
     * Gets the left cooldown of a player.
     * <b>Will return the register value if not called {@link #start}</b>
     *
     * @param player     player
     * @param identifier cooldown identifier
     * @return cooldown left
     * @throws NullPointerException if player have no cooldowns set
     * @throws NullPointerException if no cooldown found with that identifier
     */
    public long getCooldownLeft(ExtensionedPlayer player, String identifier) {
        Map<String, Long> identifierMap = cooldowns.get(player.getUUID());
        if (identifierMap == null) {
            throw new NullPointerException("No cooldowns set for player: " + player.getName());
        }
        Long time = identifierMap.get(identifier);
        if (time == null) {
            throw new NullPointerException("Cannot find cooldown for player " + player.getName() + " with identifier " + identifier);
        }
        return time;
    }

    public void start() {
        if (!Server.getInstance().getCooldownRegisterer().contains(this)) {
            throw new IllegalArgumentException("Cooldown manager not registered");
        }
        SchedulerManager sched = Server.getInstance().getSchedulerManager();
        cooldowns.forEach((uuid, map) -> {
            sched.schedule(extension, task -> {
                map.forEach((id, cooldown) -> map.replace(id, cooldown++));
                cooldowns.replace(uuid, map);
            }, new Time(0), new Time(TimeUnit.SECOND, 1));
        });
    }
}
