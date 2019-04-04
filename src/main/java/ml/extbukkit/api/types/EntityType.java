package ml.extbukkit.api.types;

import ml.extbukkit.api.world.entity.Entity;
import ml.extbukkit.main.secure.types.NamespacedKey;

public interface EntityType {
    NamespacedKey getID();

    void load(Entity entity);

    void save(Entity entity);

    boolean isTickable();

    void tick(Entity entity);
}