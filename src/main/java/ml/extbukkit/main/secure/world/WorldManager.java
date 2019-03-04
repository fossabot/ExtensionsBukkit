package ml.extbukkit.main.secure.world;

import ml.extbukkit.api.world.IWorld;
import ml.extbukkit.api.world.IWorldManager;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldManager implements IWorldManager {

    private Map<String, World> worlds = new HashMap<>();

    public WorldManager() {
        worlds.clear();
        for(org.bukkit.World w : Bukkit.getWorlds())
        {
            worlds.put(w.getName(), new World(w));
        }
    }

    @Override
    public IWorld getWorld(String name) {
        return worlds.getOrDefault(name, null);
    }

    @Override
    public String getDefaultWorld() {
        return Bukkit.getWorlds().get(0).getName();
    }

    @Override
    public List<String> getWorlds() {
        return Arrays.asList(worlds.keySet().toArray(new String[worlds.keySet().size()]));
    }
}