package net.extbukkit.api.loader;

import net.extbukkit.api.extension.Extension;
import net.extbukkit.api.log.ILogger;

import java.io.File;

public interface IExtensionLoader {
    boolean load(File extension);
    void loadAll(File dir);
}