package ml.extbukkit.main.secure.manager;

import ml.extbukkit.api.builtin.events.EventExtensionDisable;
import ml.extbukkit.api.extension.Extension;
import ml.extbukkit.api.loader.ExtensionLoader;
import ml.extbukkit.api.loader.exception.LoadException;
import ml.extbukkit.api.server.Server;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class SimpleExtensionLoader implements ExtensionLoader {

  private Map<String, Extension> extensions = new HashMap<>();
  private Map<Extension, Map<String, String>> data = new HashMap<>();
  private Server server = Server.getInstance();

  //TODO Better dependency handling
  @Override
  public void load(File extension) {
    if(!extension.exists()) {
      return;
    }
    if(!extension.getName().endsWith(".jar")) {
      return;
    }
    try {
      Set<String> cls = new HashSet<>();
      JarFile jar = new JarFile(extension);
      Enumeration<JarEntry> entires = jar.entries();
      while(entires.hasMoreElements()) {
        JarEntry entry = entires.nextElement();
        if(entry.isDirectory() || !entry.getName().endsWith(".class")) {
          continue;
        }
        cls.add(entry.getName().substring(0, entry.getName().length() - 6).replace("/", "."));
      }
      URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{extension.toURI().toURL()}, getClass().getClassLoader());
      for(String cn : cls) {
        Class<?> c = Class.forName(cn, true, classLoader);
        Class<? extends Extension> superC = c.asSubclass(Extension.class);
        Extension loadedExtensionClass = superC.newInstance();
        if(loadedExtensionClass != null) {
          loadedExtensionClass.setFile(extension);
          extensions.put(loadedExtensionClass.getID(), loadedExtensionClass);
          server.getGlobalLogger().log("Extension loaded: " + extension.getName());
          Map<String, String> map = new HashMap<>();
          map.put("description", loadedExtensionClass.getDescription());
          String authors;
          String[] authorsArr = loadedExtensionClass.getAuthors();
          if(authorsArr.length == 1) {
            authors = authorsArr[0];
          } else {
            authors = Arrays.toString(authorsArr);
          }
          map.put("authors", authors);
          map.put("version", loadedExtensionClass.getVersion());
          map.put("name", loadedExtensionClass.getName());
          map.put("id", loadedExtensionClass.getID());
          data.put(loadedExtensionClass, map);
        }
      }
    } catch(Throwable e) {
      throw new LoadException("Internal error on loading extension", e);
    }
  }

  @Override
  public void loadAll(File dir) {
    if(!dir.isDirectory()) return;
    for(File f : dir.listFiles((dir1, name) -> name.endsWith(".jar"))) {
      load(f);
    }
  }

  @Override
  public Collection<Extension> getExtensions() {
    return Collections.unmodifiableCollection(extensions.values());
  }

  @Override
  public void disable(Extension extension) {
    try {
      server.getSchedulerManager().cancelAll(extension);
    } catch(Throwable t) {
      server.getGlobalLogger().logStack("An internal error occurred trying cancelling tasks ", t);
    }
    try {
      extension.onDisable();
    } catch(Throwable t) {
      server.getGlobalLogger().logStack("An internal error occurred trying disabling extension '" + extension.getName() + "'", t);
    }
    server.getEventManager().callEvent(new EventExtensionDisable(extension));
  }

  @Override
  public Map<String, String> getExtensionData(Extension extension) {
    return Collections.unmodifiableMap(data.get(extension));
  }

}