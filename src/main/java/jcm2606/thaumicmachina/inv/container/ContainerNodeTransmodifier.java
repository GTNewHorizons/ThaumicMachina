
package jcm2606.thaumicmachina.inv.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;

import jcm2606.thaumicmachina.core.TMObjects;
import jcm2606.thaumicmachina.inv.slot.SlotLocked;
import jcm2606.thaumicmachina.tile.TileNodeTransmodifier;

public class ContainerNodeTransmodifier extends TMContainer {

    public TileNodeTransmodifier tileTransmodifier;

    public ContainerNodeTransmodifier(TileNodeTransmodifier tileTransmodifier, InventoryPlayer playerInv) {
        super(playerInv);
        this.tileTransmodifier = tileTransmodifier;
        this.loadAugmentationSlots();
        this.offsetStartPosY(0);
        this.loadPlayerInvSlots();
    }

    public void loadAugmentationSlots() {
        int x = 20;
        int y = 47;
        for (int i = 0; i < 4; ++i) {
            this.addSlotToContainer(
                new SlotLocked(this.tileTransmodifier, i, x, y, new Item[] { TMObjects.nodeAugmentation }));
            x += 40;
        }
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_) {
        return true;
    }
}
