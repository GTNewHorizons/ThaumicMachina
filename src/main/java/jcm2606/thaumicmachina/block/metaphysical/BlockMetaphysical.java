
package jcm2606.thaumicmachina.block.metaphysical;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jcm2606.thaumicmachina.block.TMBlockContainer;
import thaumcraft.api.nodes.IRevealer;

public abstract class BlockMetaphysical extends TMBlockContainer {

    public final boolean solidWithGoggles = true;

    public BlockMetaphysical(String name, Material mat) {
        super(name, mat);
        GameRegistry.registerBlock(this, name);
        this.renderAsNormalBlock = false;
        this.renderID = -1;
        this.isOpaqueCube = false;
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    @Override
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List<AxisAlignedBB> list,
        Entity entity) {
        if (entity instanceof EntityPlayer player && (hasGoggles(player) || !this.solidWithGoggles)) {
            this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
            super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
        }
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        if (hasGoggles(Minecraft.getMinecraft().thePlayer) || !this.solidWithGoggles) {
            aabb = AxisAlignedBB.getBoundingBox(
                (double) x + this.minX,
                (double) y + this.minY,
                (double) z + this.minZ,
                (double) x + this.maxX,
                (double) y + this.maxY,
                (double) z + this.maxZ);
        }
        return aabb;
    }

    @Override
    @SideOnly(value = Side.CLIENT)
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 vec1, Vec3 vec2) {
        MovingObjectPosition mop = null;
        if (!world.isRemote && (hasGoggles(Minecraft.getMinecraft().thePlayer) || !this.solidWithGoggles)) {
            mop = super.collisionRayTrace(world, x, y, z, vec1, vec2);
        }
        return mop;
    }

    public static boolean hasGoggles(EntityPlayer player) {
        int slot = 3;
        ItemStack helmet = player.getCurrentArmor(slot);
        if (helmet == null) {
            return false;
        }
        if (helmet.getItem() == null) {
            return false;
        }
        Item item = helmet.getItem();
        return item instanceof IRevealer;
    }
}
