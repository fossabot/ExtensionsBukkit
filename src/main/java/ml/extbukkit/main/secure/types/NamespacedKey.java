package ml.extbukkit.main.secure.types;

import lombok.ToString;
import ml.extbukkit.api.types.Key;

@ToString
public class NamespacedKey implements Key {

    private String namespace, key;

    public NamespacedKey(String namespace, String key) {
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