package net.extbukkit.main.server;

import net.extbukkit.api.loader.IExtensionLoader;
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

    public static void _INVALID_log(String s) {
        System.out.println(s);
    }
}