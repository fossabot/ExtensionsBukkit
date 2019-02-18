package ml.extbukkit.main.secure.types;

import ml.extbukkit.api.types.IKey;
import ml.extbukkit.api.types.IKeyMaker;

public class KeyMaker implements IKeyMaker {
    @Override
    public IKey key(String namespace, String key) {
        return new Key(namespace, key);
    }
}