package net.extbukkit.main.server;

import net.extbukkit.api.event.IEventManager;
import net.extbukkit.api.extension.Extension;
import net.extbukkit.api.loader.IExtensionLoader;
import net.extbukkit.api.log.ILogger;
import net.extbukkit.api.server.IServer;
import net.extbukkit.main.loader.ExtensionLoader;

import java.io.File;

public class Server implements IServer {
    private ExtensionLoader loader;
    private File EXTENSIONS = new File("extensions/");
    private static Server SERVER = null;

    public static IServer getInstance() {
        if(SERVER == null) SERVER = new Server();
        return SERVER;
    }
    private Server() {
        loader = new ExtensionLoader();
    }

    @Override
    public IExtensionLoader getExtensionLoader() {
        return loader;
    }

    @Override
    public File getExtensionsDir() {
        return EXTENSIONS;
    }

    //TODO Add loggers
    @Override
    public ILogger getLogger(Extension extension) {
        return null;
    }

    //TODO Add events
    @Override
    public IEventManager getEventManager(Extension extension) {
        return null;
    }
}