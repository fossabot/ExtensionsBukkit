package ml.extbukkit.api.server;

public interface IServerProperties {
    boolean isAllowEnd();
    boolean isAllowFlight();
    boolean isAllowNether();
    void getDefaultGamemode();
    boolean isGenerateStructures();
    int getIdleTimeout();
    String getIp();
    int getMaxPlayers();
    String getMOTD();
    String getServerName();
    int getPort();
    String getStopMessage();
    boolean isHardcore();
}