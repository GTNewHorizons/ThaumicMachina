
package jcm2606.thaumicmachina.block;

import jcm2606.thaumicmachina.block.TMBlock;
import jcm2606.thaumicmachina.tile.TMTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public abstract class TMBlockContainer
extends TMBlock
implements ITileEntityProvider {
    public TMBlockContainer(String name, Material mat) {
        super(name, mat);
        double d = 1.0;
    }

    public TMBlockContainer(String name, Material mat, String texture) {
        super(name, mat);
    }

    public void onBlockAdded(World world, int x, int y, int z) {
        super.onBlockAdded(world, x, y, z);
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int p_149749_6_) {
        super.breakBlock(world, x, y, z, block, p_149749_6_);
        world.removeTileEntity(x, y, z);
    }

    public boolean onBlockEventReceived(World world, int x, int y, int z, int p_149696_5_, int p_149696_6_) {
        super.onBlockEventReceived(world, x, y, z, p_149696_5_, p_149696_6_);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null ? tileentity.receiveClientEvent(p_149696_5_, p_149696_6_) : false;
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbourBlock) {
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TMTileEntity) {
            ((TMTileEntity)world.getTileEntity(x, y, z)).onNeighborBlockChange(world, x, y, z, neighbourBlock);
        }
    }

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7, float par8, float par9) {
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TMTileEntity) {
            ((TMTileEntity)world.getTileEntity(x, y, z)).onBlockActivated(world, x, y, z, player, side, par7, par8, par9);
        }
        return false;
    }

    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TMTileEntity) {
            ((TMTileEntity)world.getTileEntity(x, y, z)).onEntityWalking(world, x, y, z, entity);
        }
    }

    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TMTileEntity) {
            ((TMTileEntity)world.getTileEntity(x, y, z)).onBlockClicked(world, x, y, z, player);
        }
    }

    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TMTileEntity) {
            return ((TMTileEntity)world.getTileEntity(x, y, z)).isProvidingWeakPower(world, x, y, z, side);
        }
        return 0;
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        if (world.getTileEntity(x, y, z) != null && world.getTileEntity(x, y, z) instanceof TMTileEntity) {
            ((TMTileEntity)world.getTileEntity(x, y, z)).onEntityCollidedWithBlock(world, x, y, z, entity);
        }
    }

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {
        if (world.getTileEntity(x, y, z) instanceof TMTileEntity) {
            ((TMTileEntity)world.getTileEntity(x, y, z)).onBlockDestroyedByPlayer(world, x, y, z, meta);
        }
    }
}

