package ml.extbukkit.api.world;

import ml.extbukkit.api.types.BlockType;

/**
 * Block, that can have a type and per block handler
 */
public interface Block {
    /**
     * Get block type
     *
     * @return Block type
     */
    BlockType getType();

    /**
     * Get per block handler
     *
     * @return Per block handler
     */
    PerBlockHandler getHandler();

    /**
     * Get position of the block
     *
     * @return ExtensionLocation of the block
     */
    Position getPosition();
}