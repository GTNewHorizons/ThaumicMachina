
package jcm2606.thaumicmachina.block.metaphysical;

import cpw.mods.fml.common.registry.GameRegistry;
import jcm2606.thaumicmachina.block.metaphysical.BlockMetaphysical;
import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysicalRose;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockMetaphysicalRose
extends BlockMetaphysical {
    public BlockMetaphysicalRose() {
        super("metaphysicalRose", Material.plants);
        GameRegistry.registerTileEntity(TileMetaphysicalRose.class, (String)"tileTMMetaphysicalRose");
    }

    public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
        return new TileMetaphysicalRose();
    }
}

