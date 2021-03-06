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

import ml.extbukkit.api.cooldowns.CooldownManager;
import ml.extbukkit.api.cooldowns.CooldownManagerRegisterer;
import ml.extbukkit.api.extension.Extension;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SimpleRegisterer implements CooldownManagerRegisterer {

    private Map<Extension, CooldownManager> managers = new HashMap<>();

    @Override
    public void register(final CooldownManager manager) {
        if (managers.containsKey(manager.getExtension())) {
            throw new IllegalArgumentException("Manager already registered.");
        }
        managers.put(manager.getExtension(), manager);
    }

    @Override
    public boolean contains(final CooldownManager manager) {
        return managers.containsKey(manager.getExtension()) && managers.containsValue(manager);
    }

    @Override
    public void unregister(final CooldownManager manager) {
        managers.remove(manager.getExtension(), manager);
    }

    @Override
    public Collection<CooldownManager> getRegisteredManagers() {
        return Collections.unmodifiableCollection(managers.values());
    }
}
