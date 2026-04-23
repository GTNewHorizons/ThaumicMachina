
package jcm2606.thaumicmachina.core.helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import thaumcraft.common.tiles.TileInfusionMatrix;

public class InfusionHelper {
    public static Vec3 findMatrixRelativeTo(EntityPlayer player) {
        Vec3 vec = null;
        if (player != null) {
            for (int i = -7; i < 7; ++i) {
                for (int j = -7; j < 7; ++j) {
                    for (int k = -7; k < 7; ++k) {
                        TileEntity tile;
                        int x = (int)(player.posX + (double)i);
                        int y = (int)(player.posY + (double)j);
                        int z = (int)(player.posZ + (double)k);
                        if (player.worldObj.getTileEntity(x, y, z) == null || !((tile = player.worldObj.getTileEntity(x, y, z)) instanceof TileInfusionMatrix)) continue;
                        vec = Vec3.createVectorHelper((double)x, (double)y, (double)z);
                    }
                }
            }
        }
        return vec;
    }
}

