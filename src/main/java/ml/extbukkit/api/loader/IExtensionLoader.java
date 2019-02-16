package ml.extbukkit.api.loader;

import ml.extbukkit.api.extension.AExtension;

import java.io.File;
import java.util.Collection;
import java.util.List;

public interface IExtensionLoader {
    boolean load(File extension);
    void loadAll(File dir);
    Collection<AExtension> getExtensions();
    void reload(AExtension extension);
}