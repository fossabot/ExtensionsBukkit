package net.extbukkit.api.server;

import net.extbukkit.api.loader.IExtensionLoader;

import java.io.File;

public interface IServer {
    IExtensionLoader getExtensionLoader();
    File getExtensionsDir();
}