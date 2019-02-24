package ml.extbukkit.main.secure.server;

import ml.extbukkit.api.server.IServerProperties;
import org.bukkit.Bukkit;

public class ServerProperites implements IServerProperties {

    @Override
    public boolean isAllowEnd() {
        return Bukkit.getAllowEnd();
    }

    @Override
    public boolean isAllowFlight() {
        return Bukkit.getAllowFlight();
    }

    @Override
    public boolean isAllowNether() {
        return Bukkit.getAllowNether();
    }
    @Override
    public String getDefaultGamemode()
    {
        return Bukkit.getDefaultGameMode().name();
    }

    @Override
    public boolean isGenerateStructures() {
        return Bukkit.getGenerateStructures();
    }

    @Override
    public int getIdleTimeout() {
        return Bukkit.getIdleTimeout();
    }

    @Override
    public String getIp() {
        return Bukkit.getIp();
    }

    @Override
    public int getMaxPlayers() {
        return Bukkit.getMaxPlayers();
    }

    @Override
    public String getMOTD() {
        return Bukkit.getMotd();
    }

    @Override
    public String getServerName() {
        return Bukkit.getServerName();
    }

    @Override
    public int getPort() {
        return Bukkit.getPort();
    }

    @Override
    public String getStopMessage() {
        return Bukkit.getShutdownMessage();
    }

    @Override
    public boolean isHardcore() {
        return Bukkit.isHardcore();
    }
}