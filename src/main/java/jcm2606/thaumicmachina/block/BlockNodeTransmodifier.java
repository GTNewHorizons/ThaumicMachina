
package jcm2606.thaumicmachina.block;

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
        GameRegistry.registerBlock(this, name);
        GameRegistry.registerTileEntity(TileNodeTransmodifier.class, "tileTMNodeTransmodifier");
        this.renderAsNormalBlock = false;
        this.renderID = -1;
        this.isOpaqueCube = false;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int i) {
        return new TileNodeTransmodifier();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
        float hitY, float hitZ) {
        player.openGui(ThaumicMachina.instance, 0, world, x, y, z);
        return true;
    }
}
