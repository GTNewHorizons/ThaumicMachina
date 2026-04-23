
package jcm2606.thaumicmachina.tile.metaphysical;

import jcm2606.thaumicmachina.tile.TMTileEntity;

public class TileMetaphysical extends TMTileEntity {

    @Override
    public void updateEntity() {
        if (this.getTicks() % 1L == 9L) {
            this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord)
                .setBlockBounds(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        }
    }
}
