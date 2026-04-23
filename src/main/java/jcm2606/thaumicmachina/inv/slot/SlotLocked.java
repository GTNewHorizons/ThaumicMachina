
package jcm2606.thaumicmachina.inv.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotLocked extends Slot {

    final Item[] validItems;

    public SlotLocked(IInventory inv, int index, int x, int y, Item[] validItems) {
        super(inv, index, x, y);
        this.validItems = validItems;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        if (stack == null) return false;
        for (Item item : this.validItems) {
            if (stack.getItem() == item) {
                return true;
            }
        }
        return false;
    }
}
