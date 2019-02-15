package ml.extbukkit.main.types;

import ml.extbukkit.api.types.IKey;
import ml.extbukkit.api.types.IKeyMaker;
import ml.extbukkit.main.types.Key;

public class KeyMaker implements IKeyMaker {
    @Override
    public IKey key(String namespace, String key) {
        return new Key(namespace, key);
    }
}