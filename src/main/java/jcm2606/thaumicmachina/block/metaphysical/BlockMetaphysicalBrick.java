
package jcm2606.thaumicmachina.block.metaphysical;

import cpw.mods.fml.common.registry.GameRegistry;
import jcm2606.thaumicmachina.block.metaphysical.BlockMetaphysical;
import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysical;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMetaphysicalBrick
extends BlockMetaphysical {
    public BlockMetaphysicalBrick() {
        super("metaphysicalBrick", Material.rock);
        GameRegistry.registerTileEntity(TileMetaphysical.class, (String)"tileTMMetaphysicalBlock");
    }

    public TileEntity createNewTileEntity(World world, int i) {
        return new TileMetaphysical();
    }
}

