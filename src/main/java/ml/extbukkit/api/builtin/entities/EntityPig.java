package ml.extbukkit.api.builtin.entities;

import ml.extbukkit.api.types.IEntityType;
import ml.extbukkit.api.world.entity.IEntity;
import ml.extbukkit.main.secure.types.Key;

public class EntityPig implements IEntityType {

    @Override
    public Key getID() {
        return null;
    }

    @Override
    public void load(IEntity entity) {

    }

    @Override
    public void save(IEntity entity) {

    }


    @Override
    public boolean isTickable() {
        return false;
    }

    @Override
    public void tick(IEntity entity) {

    }
}