
package jcm2606.thaumicmachina.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import jcm2606.thaumicmachina.ThaumicMachina;
import jcm2606.thaumicmachina.tile.TileNodeTransmodifier;

public class BlockNodeTransmodifier extends TMBlockContainer {

    public BlockNodeTransmodifier(String name, Material mat) {
        super(name, mat);
        GameRegistry.registerBlock((Block) this, (String) name);
        GameRegistry.registerTileEntity(TileNodeTransmodifier.class, (String) "tileTMNodeTransmodifier");
        this.renderAsNormalBlock = false;
        this.renderID = -1;
        this.isOpaqueCube = false;
    }

    public TileEntity createNewTileEntity(World world, int p_149915_2_) {
        return new TileNodeTransmodifier();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float f1, float f2,
        float f3) {
        player.openGui((Object) ThaumicMachina.instance, 0, world, x, y, z);
        return true;
    }
}
