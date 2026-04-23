
package jcm2606.thaumicmachina.tile;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import jcm2606.thaumicmachina.client.fx.FXBeam;
import jcm2606.thaumicmachina.core.helper.ColourHelper;
import jcm2606.thaumicmachina.core.implement.IAugmentationNode;
import jcm2606.thaumicmachina.item.node.ItemNodeAugmentation;
import thaumcraft.common.tiles.TileNode;

public class TileNodeTransmodifier extends TMTileEntity implements IInventory {

    public ItemStack[] slots = new ItemStack[4];
    private float progress = 1.0f;

    @Override
    public void updateEntity() {
        super.updateEntity();
        if (this.receivingIndirectRedstoneSignal) {
            int regionBounds = 7;
            for (int i = 0 - (regionBounds / 2 + 3); i < regionBounds; ++i) {
                for (int j = 0 - (regionBounds / 2 + 3); j < regionBounds; ++j) {
                    for (int k = 0 - (regionBounds / 2 + 3); k < regionBounds; ++k) {
                        int x = this.xCoord;
                        int y = this.yCoord;
                        int z = this.zCoord;
                        TileEntity tile = this.worldObj.getTileEntity(x += i, y += j, z += k);
                        if (tile == null || !(tile instanceof TileNode)) continue;
                        TileNode node = (TileNode) tile;
                        boolean b = false;
                        for (int l = 0; l < this.slots.length; ++l) {
                            IAugmentationNode augmentation;
                            Item item;
                            ItemStack stack;
                            if (this.slots[l] == null || (stack = this.slots[l]).getItem() == null
                                || !((item = stack.getItem()) instanceof ItemNodeAugmentation)) continue;
                            ItemNodeAugmentation augmentationItem = (ItemNodeAugmentation) item;
                            IAugmentationNode[] augmentationList = new IAugmentationNode[4];
                            for (int n = 0; n < 4; ++n) {
                                ItemStack stack2 = this.getStackInSlot(n);
                                if (ItemNodeAugmentation.getAugmentation(stack2) == null) continue;
                                augmentationList[n] = ItemNodeAugmentation.getAugmentation(stack2);
                            }
                            if (ItemNodeAugmentation.getAugmentation(stack) == null
                                || !(augmentation = ItemNodeAugmentation.getAugmentation(stack))
                                    .compatible(augmentationList))
                                continue;
                            ItemNodeAugmentation.getAugmentation(stack)
                                .handle(node);
                            b = true;
                        }
                        if (this.worldObj.isRemote || !b) continue;
                        this.drawBeam(node);
                    }
                }
            }
        }
    }

    @SideOnly(value = Side.CLIENT)
    public void drawBeam(TileNode node) {
        Color[] colours = new Color[] { new Color(4915330), new Color(7089607) };
        float[] fractions = new float[] { 0.0f, 1.0f };
        this.progress = (float) Math.sqrt(Math.pow(MathHelper.sin((float) ((float) this.getTicks() / 20.0f)), 2.0));
        Color c = ColourHelper.blendColors(fractions, colours, this.progress);
        FXBeam fx = new FXBeam(
            this.getWorldObj(),
            (double) this.xCoord + 0.5,
            (double) this.yCoord + 0.75,
            (double) this.zCoord + 0.5,
            (double) node.xCoord + 0.5,
            (double) node.yCoord + 0.5,
            (double) node.zCoord + 0.5,
            10,
            1,
            "ThaumicMachina:textures/misc/beam_transmodifier.png");
        fx.speed = 0.015;
        fx.colour = c.getRGB();
        fx.opacity = 0.25f;
        fx.noClip = false;
        Minecraft.getMinecraft().effectRenderer.addEffect((EntityFX) fx);
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        ItemStack[] loadedSlots = this.loadSlotsFromNBT(compound);
        for (int i = 0; i < loadedSlots.length; ++i) {
            this.slots[i] = loadedSlots[i];
        }
    }

    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.saveSlotsToNBT(compound, this.slots);
    }

    public void doubleRecharge(TileNode node) {
        try {
            Method method = node.getClass()
                .getDeclaredMethod("handleRecharge", Boolean.TYPE);
            method.setAccessible(true);
            method.invoke(node, false);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public int getSizeInventory() {
        return this.slots.length;
    }

    public ItemStack getStackInSlot(int slot) {
        return this.slots[slot];
    }

    public ItemStack decrStackSize(int slot, int i) {
        ItemStack stack = null;
        if (this.slots[slot] != null) {
            if (this.slots[slot].stackSize <= i) {
                stack = this.slots[slot];
                this.slots[slot] = null;
            } else {
                stack = this.slots[slot].splitStack(i);
                if (this.slots[slot].stackSize == 0) {
                    this.slots[slot] = null;
                }
            }
        }
        return stack;
    }

    public ItemStack getStackInSlotOnClosing(int slot) {
        return this.slots[slot];
    }

    public void setInventorySlotContents(int slot, ItemStack stack) {
        this.slots[slot] = stack;
    }

    public String getInventoryName() {
        return "Node Transmodifier";
    }

    public boolean hasCustomInventoryName() {
        return false;
    }

    public int getInventoryStackLimit() {
        return 1;
    }

    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    public void openInventory() {}

    public void closeInventory() {}

    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }
}
