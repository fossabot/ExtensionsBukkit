package ml.extbukkit.api.loader;

import ml.extbukkit.api.extension.AExtension;

import java.io.File;
import java.util.Collection;
import java.util.Map;

/**
 * Extension loader class
 */
public interface IExtensionLoader {
    /**
     * Load an extension from jar file
     *
     * @param extension Extension file to load
     */
    void load(File extension);

    /**
     * Load all extension jar files from a folder
     *
     * @param dir Folder with extension jars
     */
    void loadAll(File dir);

    /**
     * Returns an unmodifiable collection of extensions
     *
     * @return Loaded extensions list
     */
    Collection<AExtension> getExtensions();

    /**
     * Returns an unmodifiable map of the data of the extension
     *
     * @param extension extension
     * @return data of the extension
     */
    Map<String, String> getExtensionData(AExtension extension);
}