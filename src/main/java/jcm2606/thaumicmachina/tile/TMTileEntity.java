
package jcm2606.thaumicmachina.tile;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TMTileEntity extends TileEntity {

    protected long ticks = 0L;
    public boolean receivingIndirectRedstoneSignal = false;

    @Override
    public void updateEntity() {
        for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
            if (this.getWorldObj()
                .getIndirectPowerOutput(this.xCoord, this.yCoord, this.zCoord, direction.ordinal())
                || this.getWorldObj()
                    .getIndirectPowerOutput(this.xCoord, this.yCoord - 1, this.zCoord, direction.ordinal())) {
                this.receivingIndirectRedstoneSignal = true;
                break;
            }
            this.receivingIndirectRedstoneSignal = false;
        }
        if (this.ticks == 0L) {
            this.load();
        }
        if (this.ticks == Long.MAX_VALUE) {
            this.ticks = 1L;
        }
        ++this.ticks;
    }

    public void load() {}

    public long getTicks() {
        return this.ticks;
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
        super.onDataPacket(net, pkt);
        this.readFromNBT(pkt.func_148857_g());
    }

    @Override
    public S35PacketUpdateTileEntity getDescriptionPacket() {
        NBTTagCompound compound = new NBTTagCompound();
        this.writeToNBT(compound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, -999, compound);
    }

    public void saveSlotsToNBT(NBTTagCompound compound, ItemStack[] slots) {
        NBTTagList list = new NBTTagList();
        for (int i = 0; i < slots.length; ++i) {
            NBTTagCompound slotCompound = new NBTTagCompound();
            slotCompound.setByte("Slot", (byte) i);
            if (slots[i] != null) {
                slots[i].writeToNBT(slotCompound);
            }
            list.appendTag(slotCompound);
        }
        compound.setTag("Items", list);
    }

    public ItemStack[] loadSlotsFromNBT(NBTTagCompound compound) {
        NBTTagList list = compound.getTagList("Items", 10);
        ItemStack[] slots = new ItemStack[list.tagCount()];
        for (int i = 0; i < list.tagCount(); ++i) {
            NBTTagCompound slotCompound = list.getCompoundTagAt(i);
            byte b = slotCompound.getByte("Slot");
            if (b < 0 || b >= list.tagCount()) continue;
            slots[i] = ItemStack.loadItemStackFromNBT(slotCompound);
        }
        return slots;
    }

    public Block getBlockAtCoords(int x, int y, int z) {
        return this.worldObj.getBlock(x, y, z);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbourBlock) {}

    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float par7,
        float par8, float par9) {
        return false;
    }

    public void onEntityWalking(World world, int x, int y, int z, Entity entity) {}

    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player) {}

    public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
        return 0;
    }

    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {}

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta) {}
}
