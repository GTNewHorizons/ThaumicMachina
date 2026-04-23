
package jcm2606.thaumicmachina.block.metaphysical;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import jcm2606.thaumicmachina.tile.metaphysical.TileMetaphysicalRose;

public class BlockMetaphysicalRose extends BlockMetaphysical {

    public BlockMetaphysicalRose() {
        super("metaphysicalRose", Material.plants);
        GameRegistry.registerTileEntity(TileMetaphysicalRose.class, "tileTMMetaphysicalRose");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileMetaphysicalRose();
    }
}
