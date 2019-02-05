package ml.extbukkit.api.extension;

import ml.extbukkit.api.log.ILogger;
import ml.extbukkit.api.server.IServer;
import ml.extbukkit.main.manager.ExtensionLogger;
import ml.extbukkit.main.server.Server;

public abstract class AExtension {

    public abstract String getName();
    public String getDescription() {
        return "";
    }
    public String[] getAuthors() {
        return new String[0];
    }
    public String[] getDependencies() {
        return new String[0];
    }
    public abstract String getVersion();

    // MrIvanPlays start
    public abstract void onEnable();
    public abstract void onDisable();

    public IServer getServer() {
        return Server.getInstance();
    }

    public String getFullName() {
        return getName() + " v" + getVersion();
    }

    public ILogger getLogger() {
        return new ExtensionLogger(this);
    }
    // MrIvanPlays end
}