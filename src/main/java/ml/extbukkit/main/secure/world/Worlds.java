package ml.extbukkit.main.secure.world;

import ml.extbukkit.api.world.World;
import org.bukkit.Bukkit;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Worlds implements ml.extbukkit.api.world.WorldManager {

  private Map<String, SimpleWorld> worlds = new HashMap<>();

  public Worlds() {
    worlds.clear();
    for(org.bukkit.World w : Bukkit.getWorlds()) {
      worlds.put(w.getName(), new SimpleWorld(w));
    }
  }

  @Override
  public World getWorld(String name) {
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