package ml.extbukkit.main.world;

import ml.extbukkit.api.world.StraightDirection;
import org.bukkit.block.BlockFace;

public class DirectionHelper {
    public static StraightDirection bukkitToStraight(BlockFace face) {
        if(face == null) return null;
        switch(face) {
            case UP:
                return StraightDirection.UP;
            case DOWN:
                return StraightDirection.DOWN;
            case EAST:
                return StraightDirection.EAST;
            case WEST:
                return StraightDirection.WEST;
            case NORTH:
                return StraightDirection.NORTH;
            case SOUTH:
                return StraightDirection.SOUTH;
            default:
                return null;
        }
    }
}