package ml.extbukkit.main.secure.types;

import lombok.ToString;
import ml.extbukkit.api.types.IKey;

@ToString
public class Key implements IKey {

    private String namespace, key;

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
}