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
package ml.extbukkit.main.secure.cooldowns;

import ml.extbukkit.api.builtin.events.EventExtensionDisable;
import ml.extbukkit.api.builtin.events.EventPlayerQuit;
import ml.extbukkit.api.connection.ExtensionedPlayer;
import ml.extbukkit.api.cooldowns.CooldownManager;
import ml.extbukkit.api.event.EventManager;
import ml.extbukkit.api.extension.Extension;
import ml.extbukkit.api.server.Server;

import java.util.Collection;

public class EventListener {

  public EventListener() {
    Collection<CooldownManager> managers = Server.getInstance().getCooldownRegisterer().getRegisteredManagers();
    EventManager events = Server.getInstance().getEventManager();
    events.registerHandler(EventPlayerQuit.class, event -> {
      ExtensionedPlayer player = event.getPlayer();
      managers.forEach(manager -> {
        if(manager.isRemoveOnExit()) {
          manager.getCooldowns().remove(player.getUUID());
        }
      });
    });
    events.registerHandler(EventExtensionDisable.class, event -> {
      Extension extension = event.getExtension();
      managers.forEach(manager -> {
        if(manager.getExtension().equals(extension)) {
          if(manager.isRemoveOnExit()) {
            manager.getCooldowns().clear();
          }
        }
      });
    });
  }
}
