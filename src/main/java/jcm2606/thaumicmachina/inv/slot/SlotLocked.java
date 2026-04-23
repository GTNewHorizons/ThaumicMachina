
package jcm2606.thaumicmachina.inv.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotLocked
extends Slot {
    Item[] validItems;

    public SlotLocked(IInventory inv, int index, int x, int y, Item[] validItems) {
        super(inv, index, x, y);
        this.validItems = validItems;
    }

    public boolean isItemValid(ItemStack stack) {
        boolean b = false;
        for (Item item : this.validItems) {
            if (stack == null || stack.getItem() != item) continue;
            b = true;
            break;
        }
        return b;
    }
}

