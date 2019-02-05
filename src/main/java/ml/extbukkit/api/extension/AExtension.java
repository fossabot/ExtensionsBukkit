package ml.extbukkit.api.extension;

import ml.extbukkit.api.server.IServer;
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

    public IServer getServer() {
        return Server.getInstance();
    }

    public String getFullName() {
        return getName() + " v" + getVersion();
    }
}