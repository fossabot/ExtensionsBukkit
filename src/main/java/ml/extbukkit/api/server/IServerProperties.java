package ml.extbukkit.api.server;

/**
 * Server properties class
 */
public interface IServerProperties {
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
    void getDefaultGamemode();

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
     * @return Server IP
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
     * @return Server MOTD
     */
    String getMOTD();

    /**
     * Get server name
     *
     * @return Server name
     */
    String getServerName();

    /**
     * Get server port
     *
     * @return Server port
     */
    int getPort();

    /**
     * Get server stopping message
     *
     * @return Server stopping message
     */
    String getStopMessage();

    /**
     * Is hardcore enabled
     *
     * @return true, if hardcore is enabled
     */
    boolean isHardcore();
}