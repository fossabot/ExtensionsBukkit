package ml.extbukkit.api.world;

import java.util.List;

public interface IWorldManager {
    IWorld getWorld(String name);
    String getDefaultWorld();
    List<String> getWorlds();
}