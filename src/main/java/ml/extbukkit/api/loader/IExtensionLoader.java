package ml.extbukkit.api.loader;

import java.io.File;

public interface IExtensionLoader {
    boolean load(File extension);
    void loadAll(File dir);
}