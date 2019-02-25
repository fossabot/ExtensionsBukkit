package ml.extbukkit.api.types;

import ml.extbukkit.api.world.entity.IEntity;
import ml.extbukkit.main.secure.types.Key;

public interface IEntityType {
    Key getID();

    void load(IEntity entity);

    void save(IEntity entity);

    boolean isTickable();

    void tick(IEntity entity);
}