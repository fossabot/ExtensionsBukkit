package ml.extbukkit.api.builtin.blocks;

import ml.extbukkit.api.server.Server;
import ml.extbukkit.api.types.BlockType;
import ml.extbukkit.api.types.Key;

public class TypeGrassBlock implements BlockType {

    @Override
    public Key getID() {
        return Server.getInstance().createKey("minecraft", "grass_block");
    }
}