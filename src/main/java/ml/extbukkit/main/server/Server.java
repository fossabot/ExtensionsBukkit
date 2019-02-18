package ml.extbukkit.main.server;

import ml.extbukkit.api.server.IServer;

public class Server {
    public static IServer getInstance() {
        return ml.extbukkit.main.secure.server.Server.getInstance();
    }
}