
package jcm2606.thaumicmachina.core.helper;

import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockHelper {
    public static Vec3 getCentralCoords(int x, int y, int z, ForgeDirection direction) {
        int x_ = x + direction.offsetX;
        int y_ = y + direction.offsetY;
        int z_ = z + direction.offsetZ;
        return Vec3.createVectorHelper((double)x_, (double)y_, (double)z_);
    }

    public static Vec3 getCentralCoords(int x, int y, int z, int side) {
        ForgeDirection direction = ForgeDirection.getOrientation((int)side);
        return BlockHelper.getCentralCoords(x, y, z, direction);
    }
}

