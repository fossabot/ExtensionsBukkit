package ml.extbukkit.api.world;

import ml.extbukkit.api.types.IBlockType;

/**
 * Block, that can have a type and per block handler
 */
public interface IBlock {
    /**
     * Get block type
     *
     * @return Block type
     */
    IBlockType getType();

    /**
     * Get per block handler
     *
     * @return Per block handler
     */
    IPerBlockHandler getHandler();

    /**
     * Get position of the block
     *
     * @return Position of the block
     */
    IPosition getPosition();
}