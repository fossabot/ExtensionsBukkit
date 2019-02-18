package ml.extbukkit.main.secure.types;

import ml.extbukkit.api.types.IKey;

public class Key implements IKey {
    String namespace, key;
    public Key(String namespace, String key) {
        this.namespace = namespace;
        this.key = key;
    }
    @Override
    public String getNamespace() {
        return namespace;
    }
    @Override
    public String getKey() {
        return key;
    }
    @Override
    public String toString() {
        return namespace + ":" + key;
    }
}