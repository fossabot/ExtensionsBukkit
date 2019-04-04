package ml.extbukkit.api.builtin.entities;

import ml.extbukkit.api.types.EntityType;
import ml.extbukkit.api.world.entity.Entity;
import ml.extbukkit.main.secure.types.NamespacedKey;

public class EntityPig implements EntityType {

    @Override
    public NamespacedKey getID() {
        return null;
    }

    @Override
    public void load(Entity entity) {

    }

    @Override
    public void save(Entity entity) {

    }


    @Override
    public boolean isTickable() {
        return false;
    }

    @Override
    public void tick(Entity entity) {

    }
}