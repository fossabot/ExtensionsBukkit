package net.extbukkit.main.manager;

import net.extbukkit.api.world.IWorld;
import net.extbukkit.api.world.IWorldManager;
import net.extbukkit.main.world.World;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WorldManager implements IWorldManager {
    private Map<String, World> worlds = new HashMap<>();
    boolean i = false;

    public WorldManager() {
        if(i) return;
        worlds.clear();
        for(org.bukkit.World w : Bukkit.getWorlds())
            worlds.put(w.getName(), new World(w));
        i = true;
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