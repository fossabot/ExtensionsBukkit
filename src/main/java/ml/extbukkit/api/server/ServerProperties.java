package ml.extbukkit.api.server;

/**
 * Server properties class
 */
public interface ServerProperties {
    /**
     * Is end allowed
     *
     * @return true, if end is allowed
     */
    boolean isAllowEnd();

    /**
     * Is flight allowed
     *
     * @return true, if flight is allowed
     */
    boolean isAllowFlight();

    /**
     * Is nether allowed
     *
     * @return true, if nether is allowed
     */
    boolean isAllowNether();

    /**
     * Get default gamemode
     */
    String getDefaultGamemode();

    /**
     * Do server generate structures
     *
     * @return true, if server generates structures
     */
    boolean isGenerateStructures();

    /**
     * Get idle timeout before kicking
     *
     * @return Idle timeout
     */
    int getIdleTimeout();

    /**
     * Get server IP
     *
     * @return ExtensionedServer IP
     */
    String getIp();

    /**
     * Get max player count
     *
     * @return Max player count
     */
    int getMaxPlayers();

    /**
     * Get server MOTD
     *
     * @return ExtensionedServer MOTD
     */
    String getMOTD();

    /**
     * Get server name
     *
     * @return ExtensionedServer name
     */
    String getServerName();

    /**
     * Get server port
     *
     * @return ExtensionedServer port
     */
    int getPort();

    /**
     * Get server stopping message
     *
     * @return ExtensionedServer stopping message
     */
    String getStopMessage();

    /**
     * Is hardcore enabled
     *
     * @return true, if hardcore is enabled
     */
    boolean isHardcore();
}