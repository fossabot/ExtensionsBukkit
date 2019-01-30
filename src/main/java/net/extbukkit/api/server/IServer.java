package net.extbukkit.api.server;

import net.extbukkit.api.event.IEventManager;
import net.extbukkit.api.extension.Extension;
import net.extbukkit.api.loader.IExtensionLoader;
import net.extbukkit.api.log.ILogger;

import java.io.File;

public interface IServer {
    IExtensionLoader getExtensionLoader();
    File getExtensionsDir();
    ILogger getLogger(Extension extension);
    IEventManager getEventManager();
}