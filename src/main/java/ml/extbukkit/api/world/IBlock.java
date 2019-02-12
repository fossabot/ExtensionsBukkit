package ml.extbukkit.api.world;

import ml.extbukkit.api.types.IBlockType;

public interface IBlock {
    IPosition getPosition();
    IBlockType getType();
}