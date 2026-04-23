
package jcm2606.thaumicmachina.core.helper;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class NBTHelper {

    public static NBTTagCompound getCompoundFor(ItemStack stack) {
        NBTTagCompound compound = null;
        if (stack != null) {
            if (stack.stackTagCompound == null) {
                stack.setTagCompound(new NBTTagCompound());
            }
            compound = stack.getTagCompound();
        }
        return compound;
    }
}
