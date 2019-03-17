package ml.extbukkit.api.builtin.blocks;

import ml.extbukkit.api.server.IServer;
import ml.extbukkit.api.types.IBlockType;
import ml.extbukkit.api.types.IKey;

public class TypeGrassBlock implements IBlockType {

    @Override
    public IKey getID() {
        return IServer.getInstance().createKey("minecraft", "grass_block");
    }
}