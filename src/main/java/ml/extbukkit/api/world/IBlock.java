package ml.extbukkit.api.world;

import ml.extbukkit.api.types.IBlockType;

/**
 * Block, that can have a type and per block handler
 */
public interface IBlock extends IPositionGettable {
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
}