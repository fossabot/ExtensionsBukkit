package ml.extbukkit.main.server;

import ml.extbukkit.api.server.IServer;

public class Server {
    private Server() {

    }

    /**
     * Get server instance {@link ml.extbukkit.api.server.IServer}
     *
     * @return Server instance {@link ml.extbukkit.api.server.IServer}
     */
    public static IServer getInstance() {
        return ml.extbukkit.main.secure.server.Server.getInstance();
    }
}