package ml.extbukkit.main.server;

import ml.extbukkit.api.server.IServer;

/**
 * Server retriever class
 */
public class Server {
    private Server() {

    }

    /**
     * Get server instance
     *
     * @return Server instance
     */
    public static IServer getInstance() {
        return ml.extbukkit.main.secure.server.Server.getInstance();
    }
}