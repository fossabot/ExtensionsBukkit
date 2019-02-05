package ml.extbukkit.api.extension;

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
}