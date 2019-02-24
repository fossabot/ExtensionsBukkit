package ml.extbukkit.main.secure.manager;

import ml.extbukkit.api.builtin.events.EventExtensionDisable;
import ml.extbukkit.api.builtin.events.EventExtensionReload;
import ml.extbukkit.api.loader.IExtensionLoader;
import ml.extbukkit.api.extension.AExtension;
import ml.extbukkit.api.loader.exception.LoadException;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.main.secure.server.Server;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ExtensionLoader implements IExtensionLoader {

    private Map<String, AExtension> extensions = new HashMap<>();
    private Map<AExtension, Map<String, String>> data = new HashMap<>();

    //TODO Better dependency handling
    public void load(File extension) {
        if( !extension.exists() )
        {
            return;
        }
        if( !extension.getName().endsWith( ".jar" ) )
        {
            return;
        }
        try
        {
            Set<String> cls = new HashSet<>();
            JarFile jar = new JarFile( extension );
            Enumeration<JarEntry> entires = jar.entries();
            while ( entires.hasMoreElements() )
            {
                JarEntry entry = entires.nextElement();
                if( entry.isDirectory() || !entry.getName().endsWith( ".class" ) )
                {
                    continue;
                }
                cls.add( entry.getName().substring( 0, entry.getName().length() - 6 ).replace( "/", "." ) );
            }
            URLClassLoader classLoader = URLClassLoader.newInstance( new URL[] { extension.toURI().toURL() }, getClass().getClassLoader() );
            for ( String cn : cls )
            {
                Class<?> c = Class.forName( cn, true, classLoader );
                Class<? extends AExtension> superC = c.asSubclass( AExtension.class );
                AExtension loadedExtensionClass = superC.newInstance();
                if ( loadedExtensionClass != null )
                {
                    loadedExtensionClass.setFile( extension );
                    extensions.put( loadedExtensionClass.getID(), loadedExtensionClass );
                    Server.getInstance().getGlobalLogger().log( "Extension loaded: " + extension.getName() );
                    Map<String, String> map = new HashMap<>();
                    map.put( "description", loadedExtensionClass.getDescription() );
                    String authors;
                    String[] authorsArr = loadedExtensionClass.getAuthors();
                    if ( authorsArr.length == 1 )
                    {
                        authors = authorsArr[0];
                    } else
                    {
                        authors = Arrays.toString( authorsArr );
                    }
                    map.put( "authors",  authors );
                    map.put( "version", loadedExtensionClass.getVersion() );
                    map.put( "name", loadedExtensionClass.getName() );
                    map.put( "id", loadedExtensionClass.getID() );
                    data.put( loadedExtensionClass, map );
                }
            }
        } catch ( Throwable e )
        {
            throw new LoadException( "Internal error on loading extension", e );
        }
    }

    @Override
    public void loadAll(File dir) {
        if(!dir.isDirectory()) return;
        for(File f : dir.listFiles((dir1, name) -> name.endsWith(".jar"))) {
            load(f);
        }
    }

    public List<String> getExtensionIdList() {
        List<String> ids = new ArrayList<>();
        getExtensions().forEach( extension -> ids.add( extension.getName() ) );
        return ids;
    }
    public Collection<AExtension> getExtensions() {
        return Collections.unmodifiableCollection( extensions.values() );
    }

    @Override
    public void reload(AExtension extension) {
        Server.getInstance().getEventManager().callEvent(new EventExtensionReload(extension));
    }

    @Override
    public void disable(AExtension extension)
    {
        IServer server = Server.getInstance();
        try
        {
            server.getSchedulerManager().cancelAll( extension );
        } catch ( Throwable t )
        {
            server.getGlobalLogger().logStack( "An internal error occurred trying cancelling tasks ", t );
        }
        try
        {
            extension.onDisable();
        } catch ( Throwable t )
        {
            server.getGlobalLogger().logStack( "An internal error occurred trying disabling extension '" + extension.getName() + "'", t );
        }
        Server.getInstance().getEventManager().callEvent( new EventExtensionDisable( extension ) );
    }

    @Override
    public Map<String, String> getExtensionData(AExtension extension)
    {
        return Collections.unmodifiableMap( data.get( extension ) );
    }

}