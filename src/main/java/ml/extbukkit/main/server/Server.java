package ml.extbukkit.main.server;

/**
 * Server retriever
 * <b>Change to {@link ml.extbukkit.api.server.Server}, in the future this class will be removed</b>
 */
public class Server {

    private Server() {
        throw new UnsupportedOperationException( "This class cannot be instanced" );
    }

    /**
     * Get server instance
     *
     * @return ExtensionedServer instance
     */
    public static ml.extbukkit.api.server.Server getInstance() {
        return ml.extbukkit.api.server.Server.getInstance();
    }
}