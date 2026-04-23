
package jcm2606.thaumicmachina.core.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;

import thaumcraft.common.tiles.TileInfusionMatrix;

public class InfusionHelper {

    public static Vec3 findMatrixRelativeTo(EntityPlayer player) {
        if (player != null) {
            for (int i = -7; i < 7; ++i) {
                for (int j = -7; j < 7; ++j) {
                    for (int k = -7; k < 7; ++k) {
                        int x = (int) (player.posX + (double) i);
                        int y = (int) (player.posY + (double) j);
                        int z = (int) (player.posZ + (double) k);
                        if (!(player.worldObj.getTileEntity(x, y, z) instanceof TileInfusionMatrix)) continue;
                        return Vec3.createVectorHelper(x, y, z);
                    }
                }
            }
        }
        return null;
    }
}
