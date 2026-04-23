
package jcm2606.thaumicmachina.block.metaphysical;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import jcm2606.thaumicmachina.block.TMBlockContainer;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import thaumcraft.api.nodes.IRevealer;

public abstract class BlockMetaphysical
extends TMBlockContainer {
    public boolean replaceable = false;
    public boolean solidWithGoggles = true;

    public BlockMetaphysical(String name, Material mat) {
        super(name, mat);
        GameRegistry.registerBlock((Block)this, (String)name);
        this.renderAsNormalBlock = false;
        this.renderID = -1;
        this.isOpaqueCube = false;
        this.setBlockBounds(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
    }

    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB aabb, List list, Entity entity) {
        EntityLivingBase living;
        if (entity instanceof EntityLivingBase && (living = (EntityLivingBase)entity) instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer)living;
            int slot = 3;
            boolean b = true;
            if (player.getCurrentArmor(slot) == null) {
                b = false;
            } else {
                ItemStack stack = player.getCurrentArmor(slot);
                if (stack.getItem() == null) {
                    b = false;
                } else {
                    Item item = stack.getItem();
                    if (!(item instanceof IRevealer)) {
                        b = false;
                    }
                }
            }
            if (b || !this.solidWithGoggles) {
                this.setBlockBounds(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f);
                super.addCollisionBoxesToList(world, x, y, z, aabb, list, entity);
            }
        }
    }

    @SideOnly(value=Side.CLIENT)
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z) {
        AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox((double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0, (double)0.0);
        EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
        int slot = 3;
        boolean b = true;
        if (player.getCurrentArmor(slot) == null) {
            b = false;
        } else {
            ItemStack stack = player.getCurrentArmor(slot);
            if (stack.getItem() == null) {
                b = false;
            } else {
                Item item = stack.getItem();
                if (!(item instanceof IRevealer)) {
                    b = false;
                }
            }
        }
        if (b || !this.solidWithGoggles) {
            aabb = AxisAlignedBB.getBoundingBox((double)((double)x + this.minX), (double)((double)y + this.minY), (double)((double)z + this.minZ), (double)((double)x + this.maxX), (double)((double)y + this.maxY), (double)((double)z + this.maxZ));
        }
        return aabb;
    }

    @SideOnly(value=Side.CLIENT)
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 vec1, Vec3 vec2) {
        MovingObjectPosition mop = null;
        if (!world.isRemote) {
            EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
            int slot = 3;
            boolean b = true;
            if (player.getCurrentArmor(slot) == null) {
                b = false;
            } else {
                ItemStack stack = player.getCurrentArmor(slot);
                if (stack.getItem() == null) {
                    b = false;
                } else {
                    Item item = stack.getItem();
                    if (!(item instanceof IRevealer)) {
                        b = false;
                    }
                }
            }
            if (b || !this.solidWithGoggles) {
                mop = super.collisionRayTrace(world, x, y, z, vec1, vec2);
            }
        }
        return mop;
    }
}

