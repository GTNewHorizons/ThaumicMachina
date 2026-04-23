
package jcm2606.thaumicmachina.core.helper;

import net.minecraft.util.Vec3;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockHelper {

    public static Vec3 getCentralCoords(int x, int y, int z, ForgeDirection direction) {
        return Vec3.createVectorHelper(x + direction.offsetX, y + direction.offsetY, z + direction.offsetZ);
    }

    public static Vec3 getCentralCoords(int x, int y, int z, int side) {
        return BlockHelper.getCentralCoords(x, y, z, ForgeDirection.getOrientation(side));
    }
}
