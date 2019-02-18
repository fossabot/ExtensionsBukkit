package ml.extbukkit.api.loader;

import ml.extbukkit.api.extension.AExtension;

import java.io.File;
import java.util.Collection;

/**
 * Extension loader class
 */
public interface IExtensionLoader {
    /**
     * Load an extension from jar file
     *
     * @param extension Extension file to load
     * @return true, if extension loaded successfully
     */
    boolean load(File extension);

    /**
     * Load all extension jar files from a folder
     *
     * @param dir Folder with extension jars
     */
    void loadAll(File dir);

    /**
     * Get loaded extensions list
     *
     * @return Loaded extensions list
     */
    Collection<AExtension> getExtensions();

    /**
     * Reload an extension ({@link ml.extbukkit.api.builtin.events.EventExtensionReload}
     *
     * @param extension Extension to reload
     */
    void reload(AExtension extension);
}