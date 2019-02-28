package ml.extbukkit.api.extension;

import ml.extbukkit.api.builtin.events.EventDependenciesLoaded;
import ml.extbukkit.api.log.IExtensionLogger;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.main.server.Server;

import java.io.File;
import java.io.InputStream;

/**
 * Represents a collective class which every
 * extension's main class <b>should</b>
 * extend in order to be loaded
 */
public abstract class AExtension {

    private boolean depLoaded = false;
    private File file = null;
    private IExtensionLogger logger = getServer().getLogger( getName() );
    private File dataFolder = new File( getServer().getExtensionsDir() + File.separator + getName() );

    /**
     * Called when the extension is disabled
     */
    public void onDisable()
    {
    }

    /**
     * Get extension ID<br>
     * Allowed characters: 0-9, a-z, _ (Case insensitive)
     *
     * @return Extension ID
     */
    public abstract String getID();

    /**
     * Get extension display name<br>
     * Default: Extension ID
     *
     * @return Extension name
     */
    public String getName() {
        return getID();
    }

    /**
     * Get extension description<br>
     * Default: None
     *
     * @return Extension description
     */
    public String getDescription() {
        return "";
    }

    /**
     * Get extension authors<br>
     * Default: None
     *
     * @return Extension authors
     */
    public String[] getAuthors() {
        return new String[0];
    }

    /**
     * Get extension version
     *
     * @return Extension version
     */
    public abstract String getVersion();

    /**
     * Set extension jar file if not set
     *
     * @param file Extension file
     */
    public final void setFile(File file) {
        if ( this.file != null )
        {
            return;
        }
        this.file = file;
    }

    /**
     * Called when all dependencies loaded
     */
    public final void depLoaded() {
        if (!depLoaded) {
            getServer().getEventManager().callEvent(new EventDependenciesLoaded(this));
            depLoaded = true;
        }
    }

    /**
     * Get extension jar file
     *
     * @return Extension file
     */
    public File getFile() {
        return file;
    }

    /**
     * Get server instance<br>
     * Shortcut for: {@link ml.extbukkit.main.server.Server}.getInstance();
     *
     * @return Server instance
     */
    public IServer getServer() {
        return Server.getInstance();
    }

    /**
     * Gets the extension's logger
     *
     * @return Logger instance
     */
    public IExtensionLogger getLogger() {
        return logger;
    }

    /**
     * Returns an folder with the extension's name in the extensions location
     * ex. if your extension is called "MyExtension123", the data folder will
     * be "./extensions/MyExtension123" (. stands for the path to the server files)
     *
     * @return folder with the extension's name
     */
    public File getDataFolder()
    {
        return dataFolder;
    }

    /**
     * Gets a resource as stream from the jar file of your extension
     *
     * @param resourceName got resource's name
     * @return input steam of the exact resource
     * @throws NullPointerException if the input stream (file in the jar) is null
     */
    public InputStream getResourceAsStream(String resourceName)
    {
        InputStream stream = getClass().getClassLoader().getResourceAsStream( resourceName );
        if ( stream == null )
        {
            throw new NullPointerException( "Cannot find a file into extension '" + getName() + "' with name '" + resourceName + "'" );
        }
        return stream;
    }
}