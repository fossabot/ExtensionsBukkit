package ml.extbukkit.api.builtin.blocks;

import ml.extbukkit.api.types.IBlockType;
import ml.extbukkit.api.types.IKey;
import ml.extbukkit.main.server.Server;

public class TypeGrassBlock implements IBlockType {
    @Override
    public IKey getID() {
        return Server.getInstance().getKeyMaker().key("minecraft", "grass_block");
    }
}